package nl.kabisa.service.rating;

import static nl.kabisa.service.rating.Rate.randomRatesFor;

import java.util.List;

import nl.kabisa.service.booking.Shipment;

public class RandomRateProvider implements RateProvider {

    private final String id;
    private final String apiHost;

    public RandomRateProvider(String id, String apiHost) {
        this.id = id;
        this.apiHost = apiHost;
    }

    @Override
    public List<Rate> getRates(Shipment shipment) {
        return randomRatesFor(shipment, id);
    }
}
