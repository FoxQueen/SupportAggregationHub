package crm_connector;

import commons.InvokeTest;
import commons.MockData;
import commons.MockContext;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class StrawberryCRMConnectorTest extends InvokeTest {
    @Test
    @Override
    public void invokeTest() {
        Map<String, Object> event = MockData.MOCK_STRAWBERRY_CONNECTOR_CRM;
        Map<String, Object> expectedOutput = new HashMap<>();
        expectedOutput.put("caseList", MockData.MOCK_RAW_STRAWBERRY_DATA);
        super.runTest(new Handler(new MockContext()), event, expectedOutput);
    }
}
