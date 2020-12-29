package commons;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Handler implements RequestHandler<Map<String, Object>, Map<String, Object>> {
    protected Gson gson = new GsonBuilder().create();
    protected LambdaLogger logger;
    protected final Context context;

    protected Handler(Context context) {
        this.context = context;
    }

    protected abstract void handle(Input input, Map<String, Object> response) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException;

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, com.amazonaws.services.lambda.runtime.Context context) {
        Map<String, Object> response = new HashMap<>();
        logger = context.getLogger();
        try {
            Input inputObject = gson.fromJson(gson.toJsonTree(input), Input.class);
            this.handle(inputObject, response);
            response.put("crms", inputObject.crms);
            response.put("filters", inputObject.filters);
        } catch (Exception exception) {
            logger.log(exception.getClass().getName());
            logger.log(exception.getMessage());
            logger.log(gson.toJson(exception.getStackTrace()));
            response = new HashMap<>(Response.getResponse(Response.INTERNAL_SERVER_ERROR));
            response.put("message", exception.getMessage());
        }
        return response;
    }
}