package mapper;

import commons.Context;
import commons.Input;
import commons.ReducedCases;

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
        List<ReducedCases> data = input.caseList
                .parallelStream()
                .map(caseObject -> new ReducedCases(List.of(caseObject)))
                .collect(Collectors.toList());
        response.put("reducedCasesList", data);
    }
}