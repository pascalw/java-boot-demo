package nl.kabisa.service.rating

import nl.kabisa.service.booking.Shipment

interface RateProvider {
    fun getRates(shipment: Shipment): List<Rate>
}
