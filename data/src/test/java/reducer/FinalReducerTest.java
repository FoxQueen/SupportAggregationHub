package reducer;

import commons.InvokeTest;
import commons.MockData;
import commons.MockContext;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinalReducerTest extends InvokeTest {
    @Test
    @Override
    public void invokeTest() {
        Map<String, Object> event = Map.of("reducedCasesLists", List.of(
                Map.of("reducedCasesList", MockData.MOCK_MAPPED_BANANA_DATA),
                Map.of("reducedCasesList", MockData.MOCK_MAPPED_STRAWBERRY_DATA)
        ));
        Map<String, Object> expectedOutput = new HashMap<>();
        expectedOutput.put("reducedCasesList", MockData.MOCK_FINAL_DATA);
        super.runTest(new Handler(new MockContext()), event, expectedOutput);
    }
}
