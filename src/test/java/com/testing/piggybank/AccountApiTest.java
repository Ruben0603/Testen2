package com.testing.piggybank;

import com.testing.piggybank.account.AccountResponse;
import com.testing.piggybank.account.GetAccountsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAccount() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-User-Id", "1");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Maak een GET-aanroep naar de API
        ResponseEntity<GetAccountsResponse> response = restTemplate
                .exchange("/api/v1/accounts", HttpMethod.GET, entity, GetAccountsResponse.class);

        // Controleer dat de statuscode succesvol is
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());

        // Controleer dat er precies één account is geretourneerd voor userId = 1
        Assertions.assertEquals(1, response.getBody().getAccounts().size());

        // Optioneel: Controleer de gegevens van het geretourneerde account
        AccountResponse account = response.getBody().getAccounts().get(0);
        Assertions.assertEquals("Rekening van Melvin", account.getName());
        Assertions.assertEquals(1, account.getId());
    }
}
