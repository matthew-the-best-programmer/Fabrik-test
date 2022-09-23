package com.fabrick.test.service;

import com.fabrick.test.dto.BonificoDTO;
import com.fabrick.test.dto.ResponseDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestApplicationService {

    @SpyBean
    private ApplicationService applicationService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private ObjectMapper objectMapper;

    @Test
    public void testGetBalanceException() throws JsonProcessingException {
        when(objectMapper.readValue(any(String.class),any(Class.class))).thenThrow( new JsonParseException(null,"Exception"));
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenThrow(new HttpClientErrorException( HttpStatus.BAD_REQUEST,"BAD_REQUEST", new HttpHeaders(), null, null ));
        assertThrows(JsonProcessingException.class, () -> applicationService.getBalance());
    }

    @Test
    public void testGetBalance() throws JsonProcessingException  {
        when(objectMapper.readValue(any(String.class),any(Class.class))).thenReturn( new ResponseDTO<>());
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(ResponseEntity.ok().build());
        assertDoesNotThrow(() -> applicationService.getBalance());
    }

    @Test
    public void testDoBonificoException() throws JsonProcessingException {
        when(objectMapper.readValue(any(String.class),any(Class.class))).thenThrow( new JsonParseException(null,"Exception"));
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenThrow(new HttpClientErrorException( HttpStatus.BAD_REQUEST,"BAD_REQUEST", new HttpHeaders(), null, null ));
        assertThrows(JsonProcessingException.class, () -> applicationService.doBonifico(new BonificoDTO()));
    }

    @Test
    public void testDoBonifico() throws JsonProcessingException  {
        when(objectMapper.readValue(any(String.class),any(Class.class))).thenReturn( new ResponseDTO<>());
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(ResponseEntity.ok().build());
        assertDoesNotThrow(() -> applicationService.doBonifico(new BonificoDTO()));
    }

    @Test
    public void testGetTransactionsException() throws JsonProcessingException {
        when(objectMapper.readValue(any(String.class),any(Class.class))).thenThrow( new JsonParseException(null,"Exception"));
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenThrow(new HttpClientErrorException( HttpStatus.BAD_REQUEST,"BAD_REQUEST", new HttpHeaders(), null, null ));
        assertThrows(JsonProcessingException.class, () -> applicationService.getTransactions("17","18"));
    }

    @Test
    public void testGetTransactions() throws JsonProcessingException  {
        when(objectMapper.readValue(any(String.class),any(Class.class))).thenReturn( new ResponseDTO<>());
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(ResponseEntity.ok().build());
        assertDoesNotThrow(() -> applicationService.getTransactions("17","18"));
    }

}
