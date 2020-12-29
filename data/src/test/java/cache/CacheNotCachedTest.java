package cache;

import commons.InvokeTest;
import commons.MockData;
import commons.MockContext;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CacheNotCachedTest extends InvokeTest {
    @Test
    @Override
    public void invokeTest() {
        Map<String, Object> event = MockData.MOCK_CACHE_CRMS;
        Map<String, Object> expectedOutput = new HashMap<>(MockData.MOCK_CACHE_CRMS);
        super.runTest(new Handler(new MockContext()), event, expectedOutput);
    }
}
