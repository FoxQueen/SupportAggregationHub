package filter;

import commons.Case;
import commons.Context;
import commons.Input;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Handler extends commons.Handler {
    public Handler() {
        this(new Context());
    }

    protected Handler(Context context) {
        super(context);
    }

    @Override
    protected void handle(Input input, Map<String, Object> response) {
        if (input.filters != null) {
            List<Case> data = input.caseList
                    .parallelStream()
                    .filter(caseObject -> input.filters
                            .parallelStream()
                            .allMatch(filter -> filter.pass(caseObject)))
                    .collect(Collectors.toList());
            response.put("caseList", data);
        } else {
            response.put("caseList", input.caseList);
        }
    }
}