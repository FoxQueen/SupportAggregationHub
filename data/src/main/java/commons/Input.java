package commons;

import commons.filters.Filter;
import crm_connector.connectors.CRMConnectorType;

import java.util.List;
import java.util.Map;

public class Input {
    public List<Map<String, String>> crms;
    public List<Filter> filters;
    public CRMConnectorType type;
    public String endpoint;
    public List<Case> caseList;
    public List<ReducedCases> reducedCasesList;
    public List<Map<String, List<ReducedCases>>> reducedCasesLists;
}
