package nl.kabisa.integration;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractIT {
    @LocalServerPort
    protected int serverPort;

    @BeforeClass
    public static void setup() {
        System.setProperty("rate.provider1.api.host", "http://localhost:1234");
        System.setProperty("rate.provider2.api.host", "http://localhost:1234");
    }

    protected String apiUrl(String path) {
        return "http://localhost:" + serverPort + path;
    }

    protected HttpEntity<String> jsonEntity(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(body, headers);
    }
}
