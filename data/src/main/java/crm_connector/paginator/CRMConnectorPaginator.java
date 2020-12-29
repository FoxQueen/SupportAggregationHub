package crm_connector.paginator;

import commons.Case;
import crm_connector.connectors.CRMConnector;

import java.io.IOException;
import java.util.List;

public class CRMConnectorPaginator {
    private final CRMConnector connector;
    private boolean started = false;
    private String nextToken;

    public static class CRMConnectorPaginatorReadObject {
        public final String nextToken;
        public final List<Case> page;

        public CRMConnectorPaginatorReadObject(String nextToken, List<Case> page) {
            this.nextToken = nextToken;
            this.page = page;
        }
    }

    public CRMConnectorPaginator(CRMConnector connector) {
        this.connector = connector;
    }

    public boolean hasNext() {
        return (!started || nextToken != null);
    }

    public List<Case> paginate() throws IOException {
        started = true;
        CRMConnectorPaginatorReadObject paginatorReadObject = this.connector.read(this.nextToken);
        this.nextToken = paginatorReadObject.nextToken;
        return paginatorReadObject.page;
    }
}
