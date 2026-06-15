package rw.rwandamove.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.rwandamove.entity.Trip;
import rw.rwandamove.service.TripService;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
@CrossOrigin(origins = "*")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<Trip> create(@RequestBody Trip trip) {
        return ResponseEntity.ok(tripService.createTrip(trip));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Trip>> active() {
        return ResponseEntity.ok(tripService.getActiveTrips());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Trip>> all() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Trip>> byDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(tripService.getTripsByDriver(driverId));
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<Trip> start(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.startTrip(id));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Trip> complete(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.completeTrip(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Trip> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.cancelTrip(id));
    }
}