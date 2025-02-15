package com.testing.piggybank;

import com.testing.piggybank.transaction.GetTransactionsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetTransactions() {
        ResponseEntity<GetTransactionsResponse> response = restTemplate
                .getForEntity("/api/v1/transactions/1", GetTransactionsResponse.class);

        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(4, response.getBody().getTransactions().size());
    }
}
