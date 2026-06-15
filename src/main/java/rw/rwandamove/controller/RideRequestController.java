package rw.rwandamove.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.rwandamove.entity.RideRequest;
import rw.rwandamove.service.RideRequestService;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin(origins = "*")
public class RideRequestController {

    private final RideRequestService rideService;

    public RideRequestController(RideRequestService rideService) {
        this.rideService = rideService;
    }

    @PostMapping
    public ResponseEntity<RideRequest> request(@RequestBody RideRequest req) {
        return ResponseEntity.ok(rideService.createRideRequest(req));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<RideRequest>> pending() {
        return ResponseEntity.ok(rideService.getPendingRides());
    }

    @PutMapping("/{id}/accept/{driverId}")
    public ResponseEntity<RideRequest> accept(@PathVariable Long id,
                                               @PathVariable Long driverId) {
        return ResponseEntity.ok(rideService.acceptRide(id, driverId));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<RideRequest> complete(@PathVariable Long id,
                                                 @RequestParam BigDecimal distanceKm) {
        return ResponseEntity.ok(rideService.completeRide(id, distanceKm));
    }
}