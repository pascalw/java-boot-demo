package nl.kabisa.service.booking

open class BookingException : RuntimeException() {
    class NoRatesFoundException : BookingException()
}
