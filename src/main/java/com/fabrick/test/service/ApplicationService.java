package com.fabrick.test.service;

import com.fabrick.test.Constants;
import com.fabrick.test.dto.BalanceDTO;
import com.fabrick.test.dto.BonificoDTO;
import com.fabrick.test.dto.ResponseDTO;
import com.fabrick.test.dto.TransactionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class ApplicationService {

    private final RestTemplate restTemplate;

    private final HttpHeaders h;

    private final ObjectMapper mapper;

    public ApplicationService(RestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        h = new HttpHeaders();
        h.add("Auth-Schema", Constants.AuthSchema);
        h.add("Api-Key", Constants.ApiKey);
        h.add("X-Time-Zone", "Europe/Rome");
    }
    public ResponseEntity<? extends ResponseDTO> getBalance() throws JsonProcessingException {
        HttpEntity<String> httpEntity = new HttpEntity<>(h);
        ResponseEntity<? extends ResponseDTO> res = null;
        try {
            res = restTemplate.exchange(
                    Constants.getBalanceUrl, HttpMethod.GET , httpEntity, new ResponseDTO<BalanceDTO>().getClass());
        } catch (HttpClientErrorException e) {
            e.getResponseHeaders().remove("Transfer-encoding");
            res = new ResponseEntity<>(mapper.readValue(e.getResponseBodyAsString(), ResponseDTO.class), e.getResponseHeaders(), e.getStatusCode());
        }
        return res;
    }

    public ResponseEntity<? extends ResponseDTO> doBonifico( BonificoDTO bonifico) throws JsonProcessingException {
        h.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(mapper.writeValueAsString(bonifico), h);
        ResponseEntity<? extends ResponseDTO> res = null;
        try {
            res = restTemplate.exchange(Constants.postBonificoUrl, HttpMethod.POST, httpEntity, new ResponseDTO<BonificoDTO>().getClass());
        } catch (HttpClientErrorException e) {
            e.getResponseHeaders().remove("Transfer-encoding");
            res = new ResponseEntity<>(mapper.readValue(e.getResponseBodyAsString(), ResponseDTO.class), e.getResponseHeaders(), e.getStatusCode());
        }
        h.remove(MediaType.APPLICATION_JSON);
        return res;
    }

    public ResponseEntity<? extends ResponseDTO> getTransactions (String fromAccountingDate, String toAccountingDate) throws JsonProcessingException {
        HttpEntity<String> httpEntity = new HttpEntity<>(h);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Constants.getTransactionsUrl)
                .append("?").append("fromAccountingDate=")
                .append(URLEncoder.encode(fromAccountingDate, StandardCharsets.UTF_8))
                .append("&").append("toAccountingDate=")
                .append(URLEncoder.encode(toAccountingDate, StandardCharsets.UTF_8));
        ResponseEntity<? extends ResponseDTO> res = null;
        try {
            res = restTemplate.exchange(stringBuffer.toString(), HttpMethod.GET, httpEntity, new ResponseDTO<TransactionDTO>().getClass());
        } catch (HttpClientErrorException e) {
            e.getResponseHeaders().remove("Transfer-encoding");
            res = new ResponseEntity<>(mapper.readValue(e.getResponseBodyAsString(), ResponseDTO.class), e.getResponseHeaders(), e.getStatusCode());
        }
        return res;
    }
}