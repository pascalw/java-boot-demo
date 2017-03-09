package nl.kabisa.service.rating;

import java.util.List;

import nl.kabisa.service.booking.Shipment;

public interface RateProvider {
    List<Rate> getRates(Shipment shipment);
}
