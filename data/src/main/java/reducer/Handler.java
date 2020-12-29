package reducer;

import commons.Context;
import commons.Input;
import commons.ReducedCases;

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
    protected void handle(Input input, Map<String, Object> response) {
        List<ReducedCases> reducedCasesList;
        if (input.reducedCasesLists != null) {
            reducedCasesList = new ArrayList<>();
            input.reducedCasesLists.forEach((list) -> reducedCasesList.addAll(list.get("reducedCasesList")));
        } else {
            reducedCasesList = input.reducedCasesList;
        }
        List<ReducedCases> data = new ArrayList<>();
        reducedCasesList.forEach((reducedCase) -> {
            ReducedCases aggregatedReducedCases = data
                    .parallelStream()
                    .filter(aggregatedReducedCase ->
                            aggregatedReducedCase.provider.equals(reducedCase.provider) &&
                                    aggregatedReducedCase.errorCode.equals(reducedCase.errorCode)
                    )
                    .findFirst()
                    .orElse(null);
            if (aggregatedReducedCases == null) {
                data.add(reducedCase);
            } else {
                aggregatedReducedCases.aggregate(reducedCase);
            }
        });
        response.put("reducedCasesList", data);
    }
}