package nl.kabisa.service.rating

import nl.kabisa.service.booking.Shipment
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.stream.Collectors
import java.util.stream.IntStream

data class Rate(val value: Long) {
    companion object {

        fun random(): Rate {
            return Rate(ThreadLocalRandom.current().nextLong(1, 100))
        }

        fun fakeRatesFor(shipment: Shipment): List<Rate> {
            if (shipment.customerReference == "fail")
                return emptyList()
            else
                return IntStream.range(0, 3).mapToObj { _ -> Rate.random() }.collect(Collectors.toList<Rate>())
        }

        fun cheapest(): Comparator<Rate> {
            return kotlin.Comparator { o1, o2 -> o1.value.compareTo(o2.value) }
        }
    }
}
