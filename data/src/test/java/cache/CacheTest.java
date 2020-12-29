package cache;

import commons.InvokeTest;
import commons.MockData;
import commons.MockContext;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CacheTest extends InvokeTest {
    @Test
    @Override
    public void invokeTest() {
        Map<String, Object> event = Map.of(
                "reducedCasesList", MockData.MOCK_FINAL_DATA
        );
        Map<String, Object> expectedOutput = new HashMap<>();
        expectedOutput.put("data", MockData.MOCK_FINAL_DATA);
        super.runTest(new Handler(new MockContext()), event, expectedOutput);
    }
}
