package nl.kabisa.service.booking

import nl.kabisa.service.rating.Rate
import nl.kabisa.service.rating.RateProvider
import java.util.*

class BookingService(private val rateProviders: List<RateProvider>) {

    constructor(vararg rateProviders: RateProvider) : this(Arrays.asList(*rateProviders))

    fun book(shipment: Shipment): BookingResult {
        return bookShipment(shipment)
    }

    @Throws(BookingException::class)
    fun book2(shipment: Shipment): Booking {
        return bookShipment(shipment).unwrap()
    }

    private fun bookShipment(shipment: Shipment): BookingResult {
        val cheapestRate = cheapestRate(shipment)

        return when (cheapestRate) {
            null -> BookingResult.err(BookingException.NoRatesFoundException())
            else -> BookingResult.ok(Booking(cheapestRate))
        }
    }

    private fun cheapestRate(shipment: Shipment): Rate? {
        return rateProviders
                .flatMap({ provider -> provider.getRates(shipment) })
                .minWith(Rate.cheapest())

    }
}
