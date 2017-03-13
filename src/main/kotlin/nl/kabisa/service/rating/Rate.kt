package nl.kabisa.service.rating

import nl.kabisa.service.booking.Shipment
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.stream.Collectors
import java.util.stream.IntStream

data class Rate(val value: Long) {
    companion object {

        fun fakeRatesFor(shipment: Shipment): List<Rate> {
            if (shipment.customerReference == "fail")
                return emptyList()
            else
                return IntStream.range(0, 3).mapToObj { _ -> Rate.random() }.collect(Collectors.toList<Rate>())
        }

        fun random(): Rate = Rate(ThreadLocalRandom.current().nextLong(1, 100))

        fun cheapest(): Comparator<Rate> = kotlin.Comparator { (val1), (val2) -> val1.compareTo(val2) }
    }
}
