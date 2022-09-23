package com.fabrick.test.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO<T> {
    private String status;
    private List errors;
    private T payload;
}
