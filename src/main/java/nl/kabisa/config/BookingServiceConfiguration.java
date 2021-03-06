package nl.kabisa.config;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.google.common.collect.ImmutableList;

import nl.kabisa.service.booking.BookingService;
import nl.kabisa.service.rating.*;

@Configuration
public class BookingServiceConfiguration {

    @Configuration
    @ConditionalOnMissingBean(MockRateProviders.class)
    public static class RealRateProviders {
        @Bean
        public RandomRateProvider rateProvider1(Environment environment) {
            return new RandomRateProvider("provider1", environment.getRequiredProperty("rate.provider1.api.host"));
        }

        @Bean
        public RandomRateProvider rateProvider2(Environment environment) {
            return new RandomRateProvider("provider2", environment.getRequiredProperty("rate.provider2.api.host"));
        }
    }

    @Configuration
    @ConditionalOnProperty("mock.rates")
    public static class MockRateProviders {
        @Bean
        public RateProvider mockRateProvider() {
            return shipment -> ImmutableList.of(Rate.random("random"));
        }
    }

    @Bean
    public BookingService bookingService(List<RateProvider> rateProviders) {
        return new BookingService(rateProviders);
    }
}
