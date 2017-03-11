package nl.kabisa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nl.kabisa.service.booking.*;

@RestController
public class BookShipmentController extends ApplicationController {

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
    public ResponseEntity<ErrorResponse> handleNoRatesFound() {
        return new ResponseEntity<>(new ErrorResponse("No rates found"), HttpStatus.CONFLICT);
    }

}
