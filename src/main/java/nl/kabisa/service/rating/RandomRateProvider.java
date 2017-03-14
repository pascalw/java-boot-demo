package nl.kabisa.service.rating;

import static nl.kabisa.service.rating.Rate.randomRatesFor;

import java.util.List;

import nl.kabisa.service.booking.Shipment;

public class RandomRateProvider implements RateProvider {

    private final String apiHost;

    public RandomRateProvider(String apiHost) {
        this.apiHost = apiHost;
    }

    @Override
    public List<Rate> getRates(Shipment shipment) {
        return randomRatesFor(shipment);
    }
}
