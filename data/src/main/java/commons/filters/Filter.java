package commons.filters;

import commons.Case;

import java.io.Serializable;

public abstract class Filter implements Serializable {
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Filter)) {
            return false;
        }
        Filter otherCase = (Filter) other;
        return true;
    }

    public abstract boolean pass(Case value);
}
