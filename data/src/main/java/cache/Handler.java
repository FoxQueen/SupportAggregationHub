package cache;

import commons.Context;
import commons.Input;
import commons.ReducedCases;

import java.util.List;
import java.util.Map;

public class Handler extends commons.Handler {
    public Handler() {
        this(new Context());
    }

    protected Handler(Context context) {
        super(context);
    }

    @Override
    protected void handle(Input input, Map<String, Object> response) {
        List<ReducedCases> data;
        if (input.reducedCasesList != null) {
            data = input.reducedCasesList;
            context.cacheItem(gson.toJson(input.crms), data);
        } else {
            data = context.getCachedItem(gson.toJson(input.crms));
        }
        if (data != null) {
            response.put("data", data);
        }
    }
}