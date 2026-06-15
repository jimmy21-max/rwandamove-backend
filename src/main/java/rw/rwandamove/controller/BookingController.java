package rw.rwandamove.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.rwandamove.entity.Booking;
import rw.rwandamove.service.BookingService;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    @GetMapping("/passenger/{passengerId}")
    public ResponseEntity<List<Booking>> byPassenger(@PathVariable Long passengerId) {
        return ResponseEntity.ok(bookingService.getPassengerBookings(passengerId));
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Booking>> byTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(bookingService.getTripPassengers(tripId));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }
}