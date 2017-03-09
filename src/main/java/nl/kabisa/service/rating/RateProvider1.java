package nl.kabisa.service.rating;

import static nl.kabisa.service.rating.Rate.fakeRatesFor;

import java.util.List;

import nl.kabisa.service.booking.Shipment;

public class RateProvider1 implements RateProvider {

    private final String apiHost;

    public RateProvider1(String apiHost) {
        this.apiHost = apiHost;
    }

    @Override
    public List<Rate> getRates(Shipment shipment) {
        return fakeRatesFor(shipment);
    }
}
