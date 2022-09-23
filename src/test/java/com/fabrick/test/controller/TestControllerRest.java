package com.fabrick.test.controller;

import com.fabrick.test.ControllerRest;
import com.fabrick.test.dto.BonificoDTO;
import com.fabrick.test.service.ApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestControllerRest {

    @SpyBean
    ControllerRest controllerRest;

    @MockBean
    ApplicationService applicationService;

    @Test
    public void testGetBalance() throws JsonProcessingException {
        when(applicationService.getBalance()).thenReturn(ResponseEntity.ok().build());
        assertDoesNotThrow(() -> controllerRest.getBalance());
    }

    @Test
    public void testDoBonifico() throws JsonProcessingException {
        when(applicationService.doBonifico(new BonificoDTO())).thenReturn(ResponseEntity.ok().build());
        assertDoesNotThrow(() -> controllerRest.doBonifico(new BonificoDTO()));
    }

    @Test
    public void testDoBonificoException() throws JsonProcessingException {
        assertThrows(IllegalArgumentException.class ,() -> controllerRest.doBonifico(null));
    }

    @Test
    public void testGetTransactions() throws JsonProcessingException {
        when(applicationService.getTransactions(any(String.class),  any(String.class))).thenReturn(ResponseEntity.ok().build());
        assertDoesNotThrow(() -> controllerRest.doBonifico(new BonificoDTO()));
    }

    @Test
    public void testGetTransactionsException1() throws JsonProcessingException {
        assertThrows(IllegalArgumentException.class ,() -> controllerRest.getTransactions(null, null));
    }
    @Test
    public void testGetTransactionsException2() throws JsonProcessingException {
        assertThrows(IllegalArgumentException.class ,() -> controllerRest.getTransactions("abc", null));
    }
    @Test
    public void testGetTransactionsException3() throws JsonProcessingException {
        assertThrows(IllegalArgumentException.class ,() -> controllerRest.getTransactions(null, "abc"));
    }

}
