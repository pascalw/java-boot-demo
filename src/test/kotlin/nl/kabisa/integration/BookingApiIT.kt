package nl.kabisa.integration

import org.junit.Assert.assertEquals
import org.junit.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod

class BookingApiIT : AbstractIT() {
    protected val restTemplate = TestRestTemplate().restTemplate

    @Test
    fun booking_a_shipment() {
        val requestBody = """{
            "customerReference": "ref",
            "height": 100,
            "weight": 5,
            "length": 10
        }"""

        val response = restTemplate.exchange(
                apiUrl("/booking"),
                HttpMethod.POST,
                jsonEntity(requestBody), String::class.java)

        assertEquals(200, response.statusCodeValue.toLong())
        JSONAssert.assertEquals("{"
                + "  \"id\": 670,"
                + "  \"rate\": {"
                + "    \"value\": 22"
                + "  }\n"
                + "}", response.body, true)
    }
}
