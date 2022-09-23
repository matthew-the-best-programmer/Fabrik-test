package com.fabrick.test.dto;

import lombok.Data;


@Data
public class AccountDTO {

    private Long accountId;

    private String iban;

    private Long abiCode;

    private Long cabCode;

    private String countryCode;

    private Long internationalCin;

    private String nationalCin;

    private Long account;

    private String alias;

    private String productName;

    private String holderName;

    private String activatedDate;

    private String currency;
}
