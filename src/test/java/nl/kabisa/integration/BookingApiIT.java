package nl.kabisa.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BookingApiIT extends AbstractIT {
    protected final RestTemplate restTemplate = new TestRestTemplate().getRestTemplate();

    @Test
    public void booking_a_shipment() {
        String requestBody = "{"
                             + "    \"customerReference\": \"ref\","
                             + "    \"height\": 100,"
                             + "    \"weight\": 5,"
                             + "    \"length\": 10"
                             + "}";

        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl("/booking"),
                HttpMethod.POST,
                jsonEntity(requestBody), String.class);

        assertEquals(200, response.getStatusCodeValue());
        JSONAssert.assertEquals("{"
                                + "  \"id\": 670,"
                                + "  \"rate\": {"
                                + "    \"value\": 22"
                                + "  }\n"
                                + "}", response.getBody(), true);
    }
}
