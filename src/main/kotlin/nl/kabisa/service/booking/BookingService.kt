package nl.kabisa.service.booking

import nl.kabisa.service.rating.Rate
import nl.kabisa.service.rating.RateProvider
import nl.kabisa.util.Result
import java.util.*

class BookingService(private val rateProviders: List<RateProvider>) {

    constructor(vararg rateProviders: RateProvider) : this(Arrays.asList(*rateProviders))

    fun book(shipment: Shipment): Result<Booking, BookingException> {
        return bookShipment(shipment)
    }

    @Throws(BookingException::class)
    fun book2(shipment: Shipment): Booking {
        return bookShipment(shipment).unwrap()
    }

    private fun bookShipment(shipment: Shipment): Result<Booking, BookingException> {
        val cheapestRate = cheapestRate(shipment)

        return when (cheapestRate) {
            null -> Result.err<Booking, BookingException>(BookingException.NoRatesFoundException())
            else -> Result.ok<Booking, BookingException>(Booking(cheapestRate))
        }
    }

    private fun cheapestRate(shipment: Shipment): Rate? {
        return rateProviders
                .flatMap({ provider -> provider.getRates(shipment) })
                .minWith(Rate.cheapest())

    }
}
