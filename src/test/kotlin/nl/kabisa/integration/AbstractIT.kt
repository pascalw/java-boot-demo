package nl.kabisa.integration

import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class AbstractIT {
    @LocalServerPort
    protected var serverPort: Int = 0

    protected fun apiUrl(path: String): String {
        return "http://localhost:" + serverPort + path
    }

    protected fun jsonEntity(body: String): HttpEntity<String> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        return HttpEntity(body, headers)
    }

    companion object {

        @BeforeClass @JvmStatic
        fun setup() {
            System.setProperty("rate.provider1.api.host", "http://localhost:1234")
            System.setProperty("rate.provider2.api.host", "http://localhost:1234")
        }
    }
}
