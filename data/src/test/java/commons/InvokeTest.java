package commons;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class InvokeTest {
    private static final Logger logger = LoggerFactory.getLogger(InvokeTest.class);

    protected void runTest(
            RequestHandler<Map<String, Object>, Map<String, Object>> handler,
            Map<String, Object> event,
            Map<String, Object> expectedOutput
    ) {
        try {
            logger.info("Running test for: " + handler.getClass().getSimpleName());
            Context context = new commons.TestContext();
            Map<String, Object> output = handler.handleRequest(event, context);
            if (!expectedOutput.containsKey("crms")) {
                expectedOutput.put("crms", null);
            }
            if (!expectedOutput.containsKey("filters")) {
                expectedOutput.put("filters", null);
            }
            assertEquals(expectedOutput, output);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @SuppressWarnings("unused")
    public abstract void invokeTest();
}