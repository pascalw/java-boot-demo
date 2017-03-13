package nl.kabisa.config

import nl.kabisa.service.booking.BookingService
import nl.kabisa.service.booking.Shipment
import nl.kabisa.service.rating.Rate
import nl.kabisa.service.rating.RateProvider
import nl.kabisa.service.rating.RateProvider1
import nl.kabisa.service.rating.RateProvider2
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
open class BookingServiceConfiguration {

    @Configuration
    @ConditionalOnMissingBean(MockRateProviders::class)
    open class RealRateProviders {
        @Bean
        open fun rateProvider1(environment: Environment): RateProvider1 {
            return RateProvider1(environment.getRequiredProperty("rate.provider1.api.host"))
        }

        @Bean
        open fun rateProvider2(environment: Environment): RateProvider2 {
            return RateProvider2(environment.getRequiredProperty("rate.provider2.api.host"))
        }
    }

    @Configuration
    @ConditionalOnProperty("mock.rates")
    open class MockRateProviders {
        @Bean
        open fun mockRateProvider(): RateProvider {
            return object : RateProvider {
                override fun getRates(shipment: Shipment): List<Rate> = listOf(Rate.random())
            }
        }
    }

    @Bean
    open fun bookingService(rateProviders: List<RateProvider>): BookingService {
        return BookingService(rateProviders)
    }
}
