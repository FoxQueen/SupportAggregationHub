package sterilizer;

import commons.Case;
import commons.Context;
import commons.Input;

import java.util.Arrays;
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
        String supportedProductsString = context.getEnvironmentVariable("SUPPORTED_PRODUCTS");
        List<String> supportedProducts = Arrays.asList(
                supportedProductsString.split(",")
        );
        List<Case> data = input.caseList
                .parallelStream()
                .filter(caseObject -> supportedProducts.contains(caseObject.product))
                .collect(Collectors.toList());
        response.put("caseList", data);
    }
}