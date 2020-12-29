package crm_connector.connectors;

import com.google.gson.reflect.TypeToken;
import commons.Case;
import commons.Input;
import crm_connector.paginator.CRMConnectorPaginator;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BananaCRMConnector extends SampleCRMConnector {
    public BananaCRMConnector(Input config) {
        super(config);
    }

    @Override
    public CRMConnectorPaginator.CRMConnectorPaginatorReadObject read(String nextToken) throws IOException {
        Type listType = new TypeToken<ArrayList<List<Case>>>() {}.getType();
        List<List<Case>> data = gson.fromJson(this.getRawData(), listType);
        return new CRMConnectorPaginator.CRMConnectorPaginatorReadObject(null, data.get(0));
    }
}
