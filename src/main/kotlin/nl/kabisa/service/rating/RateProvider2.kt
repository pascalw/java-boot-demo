package nl.kabisa.service.rating

import nl.kabisa.service.booking.Shipment

class RateProvider2(private val apiHost: String) : RateProvider {
    override fun getRates(shipment: Shipment): List<Rate> = Rate.fakeRatesFor(shipment)
}
