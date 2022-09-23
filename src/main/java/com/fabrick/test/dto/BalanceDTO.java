package com.fabrick.test.dto;

import lombok.Data;

@Data
public class BalanceDTO {
        private String date;
        private Double balance;
        private Double availableBalance;
        private String currency;
}
