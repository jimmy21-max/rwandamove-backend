package rw.rwandamove.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.rwandamove.entity.Vehicle;
import rw.rwandamove.service.VehicleService;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/register/{driverId}")
    public ResponseEntity<Vehicle> register(@PathVariable Long driverId,
                                             @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.registerVehicle(driverId, vehicle));
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Vehicle>> byDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(vehicleService.getDriverVehicles(driverId));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Vehicle>> pending() {
        return ResponseEntity.ok(vehicleService.getPendingVehicles());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Vehicle> approve(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.approveVehicle(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Vehicle> reject(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.rejectVehicle(id));
    }
}