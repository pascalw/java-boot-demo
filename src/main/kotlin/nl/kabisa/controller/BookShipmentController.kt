package nl.kabisa.controller

import nl.kabisa.service.booking.Booking
import nl.kabisa.service.booking.BookingException
import nl.kabisa.service.booking.BookingService
import nl.kabisa.service.booking.Shipment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class BookShipmentController(val bookingService: BookingService) : ApplicationController() {

    @PostMapping("/booking")
    fun bookShipment(@RequestBody @Valid shipment: Shipment): Booking {
        return bookingService.book(shipment).unwrap()
    }

    @ExceptionHandler(BookingException.NoRatesFoundException::class)
    fun handleNoRatesFound(): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("No rates found"), HttpStatus.CONFLICT)
    }
}
