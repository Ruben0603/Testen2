package com.testing.piggybank;

import com.testing.piggybank.account.GetAccountsResponse;
import com.testing.piggybank.account.UpdateAccountRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/api/v1/accounts";

    private HttpHeaders createHeadersWithUserId(String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-User-Id", userId);
        return headers;
    }

    private HttpEntity<Void> createRequestEntityWithHeaders(HttpHeaders headers) {
        return new HttpEntity<>(headers);
    }

    private HttpEntity<UpdateAccountRequest> createUpdateRequest(UpdateAccountRequest updateAccountRequest) {
        return new HttpEntity<>(updateAccountRequest);
    }

    @Test
    public void testGetAccounts_Success() {
        HttpHeaders headers = createHeadersWithUserId("1");
        HttpEntity<Void> requestEntity = createRequestEntityWithHeaders(headers);

        ResponseEntity<GetAccountsResponse> response = restTemplate
                .exchange(BASE_URL, org.springframework.http.HttpMethod.GET, requestEntity, GetAccountsResponse.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().getAccounts().isEmpty(), "Er moeten accounts beschikbaar zijn.");
    }

    @Test
    public void testGetAccounts_Unauthorized() {
        HttpEntity<Void> requestEntity = createRequestEntityWithHeaders(null);

        ResponseEntity<GetAccountsResponse> response = restTemplate
                .exchange(BASE_URL, org.springframework.http.HttpMethod.GET, requestEntity, GetAccountsResponse.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdateAccount_Success() {
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
        updateAccountRequest.setAccountId(1L);
        updateAccountRequest.setAccountName("Updated Account Name");
        HttpEntity<UpdateAccountRequest> requestEntity = createUpdateRequest(updateAccountRequest);

        ResponseEntity<Void> response = restTemplate
                .exchange(BASE_URL, org.springframework.http.HttpMethod.PUT, requestEntity, Void.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
