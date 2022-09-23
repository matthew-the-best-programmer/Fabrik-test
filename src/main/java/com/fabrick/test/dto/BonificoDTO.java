package com.fabrick.test.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class BonificoDTO {

    private Long accountId;

    private Creditor creditor;

    private String receiverName;

    private String description;

    private String currency;

    private String amount;

    private String executionDate;

    @Data
    private class Creditor {

        private String name;

        private Account account;

        @Data
        private class Account {
            private String accountCode;
        }
    }

}
