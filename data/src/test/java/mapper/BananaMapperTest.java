package mapper;

import commons.InvokeTest;
import commons.MockData;
import commons.MockContext;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BananaMapperTest extends InvokeTest {
    @Test
    @Override
    public void invokeTest() {
        Map<String, Object> event = Map.of("caseList", MockData.MOCK_STERILE_BANANA_DATA);
        Map<String, Object> expectedOutput = new HashMap<>();
        expectedOutput.put("reducedCasesList", MockData.MOCK_MAPPED_BANANA_DATA);
        super.runTest(new Handler(new MockContext()), event, expectedOutput);
    }
}
