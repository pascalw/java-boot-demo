package nl.kabisa.service.rating

import nl.kabisa.service.booking.Shipment

class RateProvider1(private val apiHost: String) : RateProvider {

    override fun getRates(shipment: Shipment): List<Rate> {
        return Rate.fakeRatesFor(shipment)
    }
}
