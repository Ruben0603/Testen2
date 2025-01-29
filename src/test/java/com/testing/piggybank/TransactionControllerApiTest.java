package com.testing.piggybank;


import com.testing.piggybank.model.Currency;
import com.testing.piggybank.transaction.CreateTransactionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_createTransaction_responseOk() {
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setAmount(new BigDecimal(100));
        createTransactionRequest.setCurrency(Currency.EURO);
        createTransactionRequest.setReceiverAccountId(1L);
        createTransactionRequest.setSenderAccountId(2L);
        createTransactionRequest.setDescription("test transaction");

        HttpEntity<CreateTransactionRequest> request = new HttpEntity<>(createTransactionRequest);

        ResponseEntity<HttpStatus> response = restTemplate.postForEntity("/api/v1/transactions", request, HttpStatus.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
