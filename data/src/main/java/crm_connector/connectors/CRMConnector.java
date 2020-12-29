package crm_connector.connectors;

import commons.Input;
import crm_connector.paginator.CRMConnectorPaginator;

import java.io.IOException;

public abstract class CRMConnector {
    protected Input config;

    public CRMConnector(Input config) {
        this.config = config;
    }

    public CRMConnectorPaginator getPaginator() {
        return new CRMConnectorPaginator(this);
    }

    public abstract CRMConnectorPaginator.CRMConnectorPaginatorReadObject read(String nextToken) throws IOException;
}
