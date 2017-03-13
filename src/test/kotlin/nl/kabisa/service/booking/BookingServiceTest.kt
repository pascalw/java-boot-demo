package nl.kabisa.service.booking

import nl.kabisa.service.rating.Rate
import nl.kabisa.service.rating.RateProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BookingServiceTest {

    private val cheapProvider: RateProvider = object : RateProvider {
        override fun getRates(shipment: Shipment): List<Rate> = listOf(CHEAPEST_RATE)
    }

    private val expensiveProvider: RateProvider = object : RateProvider {
        override fun getRates(shipment: Shipment): List<Rate> = listOf(Rate(100))
    }

    private val bookingService = BookingService(cheapProvider, expensiveProvider)
    private val shipment = Shipment("ref", 100, 10, 20, "Amaze balls!")

    @Test
    fun books_with_cheapest_rate() {
        val result = bookingService.book(shipment)

        assertTrue(result.isOk)
        assertEquals(CHEAPEST_RATE, result.result!!.rate)
    }

    @Test
    fun error_when_no_rates_available() {
        val result = BookingService().book(shipment)

        assertTrue(result.isErr)
        assertTrue(result.exception is BookingException.NoRatesFoundException)
    }

    companion object {
        private val CHEAPEST_RATE = Rate(1)
    }
}
