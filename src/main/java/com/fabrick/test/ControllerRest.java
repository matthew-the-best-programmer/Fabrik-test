package com.fabrick.test;

import com.fabrick.test.dto.BonificoDTO;
import com.fabrick.test.dto.ResponseDTO;
import com.fabrick.test.service.ApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControllerRest {

    private final ApplicationService applicationService;

    public ControllerRest(ApplicationService applicationService){
        this.applicationService = applicationService;
    }

    @GetMapping("/balance")
    public ResponseEntity<? extends ResponseDTO> getBalance() throws JsonProcessingException {
        return applicationService.getBalance();
    }

    @PostMapping("/money-transfers")
    public ResponseEntity<? extends ResponseDTO> doBonifico(@RequestBody BonificoDTO bonifico) throws JsonProcessingException {
        if (bonifico == null) {
            throw new IllegalArgumentException("Invalid requestBody");
        }
        bonifico.setAccountId(Constants.accountId);
        return applicationService.doBonifico(bonifico);
    }

    @GetMapping("/transactions")
    public ResponseEntity<? extends ResponseDTO> getTransactions(String fromAccountingDate, String toAccountingDate) throws JsonProcessingException {
        if (fromAccountingDate == null || toAccountingDate == null) {
            throw new IllegalArgumentException("Invalid params");
        }
        return applicationService.getTransactions(fromAccountingDate, toAccountingDate);
    }

}
