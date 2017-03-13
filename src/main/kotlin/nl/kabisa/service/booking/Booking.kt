package nl.kabisa.service.booking

import nl.kabisa.service.rating.Rate
import java.util.concurrent.ThreadLocalRandom

data class Booking(val rate: Rate) {
    val id: Long = ThreadLocalRandom.current().nextInt(0, 1000).toLong()
}
