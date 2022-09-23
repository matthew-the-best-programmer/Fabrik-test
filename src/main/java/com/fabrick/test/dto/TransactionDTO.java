package com.fabrick.test.dto;

import lombok.Data;

@Data
public class TransactionDTO {

    private Long transactionId;
    private Long operationId;
    private String accountingDate;
    private String valueDate;
    private Type type;
    private Long amount;
    private String currency;
    private String description;

    @Data
    private class Type {
        private String enumeration;
        private String value;
    }

}
