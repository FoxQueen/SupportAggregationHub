package crm_connector;

import commons.Case;
import commons.Context;
import commons.Input;
import crm_connector.connectors.CRMConnector;
import crm_connector.paginator.CRMConnectorPaginator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Handler extends commons.Handler {
    public Handler() {
        this(new Context());
    }

    protected Handler(Context context) {
        super(context);
    }

    @Override
    protected void handle(Input input, Map<String, Object> response) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        CRMConnector connector = input.type.create(input);
        CRMConnectorPaginator paginator = connector.getPaginator();
        List<Case> data = new ArrayList<>();
        while (paginator.hasNext()) {
            data.addAll(paginator.paginate());
        }
        response.put("caseList", data);
    }
}