package com.fox.main.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Case implements Serializable {
    @SerializedName(value = "caseId", alternate = "CaseID")
    public Long caseId;
    @SerializedName(value = "customerId", alternate = "CustomerID")
    public Long customerId;
    @SerializedName(value = "provider", alternate = "Provider")
    public Long provider;
    @SerializedName(value = "errorCode", alternate = "CREATED_ERROR_CODE")
    public Long errorCode;
    @SerializedName(value = "status", alternate = "STATUS")
    public CaseStatus status;
    @SerializedName(value = "creationDate", alternate = "TICKET_CREATION_DATE")
    public String creationDate;
    @SerializedName(value = "modificationDate", alternate = "LAST_MODIFIED_DATE")
    public String modificationDate;
    @SerializedName(value = "product", alternate = "PRODUCT_NAME")
    public String product;

    public enum CaseStatus {
        @SerializedName(value = "OPEN", alternate = "Open")
        OPEN,
        @SerializedName(value = "CLOSED", alternate = "Closed")
        CLOSED
    }

    public Case(
            Long caseId,
            Long customerId,
            Long provider,
            Long errorCode,
            CaseStatus status,
            String creationDate,
            String modificationDate,
            String product
    ) {
        this.caseId = caseId;
        this.customerId = customerId;
        this.provider = provider;
        this.errorCode = errorCode;
        this.status = status;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.product = product;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Case)) {
            return false;
        }
        Case otherCase = (Case) other;
        return this.caseId.equals(otherCase.caseId) &&
                this.customerId.equals(otherCase.customerId) &&
                this.provider.equals(otherCase.provider) &&
                this.errorCode.equals(otherCase.errorCode) &&
                this.status.equals(otherCase.status) &&
                this.creationDate.equals(otherCase.creationDate) &&
                this.modificationDate.equals(otherCase.modificationDate) &&
                this.product.equals(otherCase.product);
    }
}
