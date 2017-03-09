package nl.kabisa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.kabisa.service.booking.*;

@RestController
public class BookShipmentController {

    private final BookingService bookingService;

    @Autowired
    public BookShipmentController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public Booking bookShipment(@RequestBody @Valid Shipment shipment) {
        return bookingService.book(shipment).unwrap();
    }

    @ExceptionHandler(BookingException.NoRatesFoundException.class)
    public ResponseEntity<Error> handleNoRatesFound() {
        return new ResponseEntity<>(new Error("No rates found"), HttpStatus.CONFLICT);
    }

    private static class Error {
        @JsonProperty("error")
        public final String message;

        private Error(String message) {
            this.message = message;
        }
    }
}
