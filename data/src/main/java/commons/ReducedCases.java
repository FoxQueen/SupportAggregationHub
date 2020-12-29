package commons;

import java.util.ArrayList;
import java.util.List;

public class ReducedCases {
    public Long errorCode;
    public Long provider;
    public final List<String> products = new ArrayList<>();
    public Integer openCasesCount = 0;
    public final List<Case> cases;

    public ReducedCases(List<Case> cases) {
        this.cases = cases;
        cases.forEach((caseObject) -> {
            this.errorCode = caseObject.errorCode;
            this.provider = caseObject.provider;
            if (!this.products.contains(caseObject.product)) {
                this.products.add(caseObject.product);
            }
            if (caseObject.status == Case.CaseStatus.OPEN) {
                ++this.openCasesCount;
            }
        });
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ReducedCases)) {
            return false;
        }
        ReducedCases otherReducedCases = (ReducedCases) other;
        return this.errorCode.equals(otherReducedCases.errorCode) &&
                this.provider.equals(otherReducedCases.provider) &&
                this.products.equals(otherReducedCases.products) &&
                this.openCasesCount.equals(otherReducedCases.openCasesCount) &&
                this.cases.equals(otherReducedCases.cases);
    }

    public void aggregate(ReducedCases reducedCases) {
        reducedCases.products.forEach((product) -> {
            if (!this.products.contains(product)) {
                this.products.add(product);
            }
        });
        this.openCasesCount += reducedCases.openCasesCount;
        this.cases.addAll(reducedCases.cases);
    }
}
