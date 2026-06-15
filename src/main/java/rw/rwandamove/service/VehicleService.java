package rw.rwandamove.service;

import org.springframework.stereotype.Service;
import rw.rwandamove.entity.Vehicle;
import rw.rwandamove.entity.User;
import rw.rwandamove.repository.VehicleRepository;
import rw.rwandamove.repository.UserRepository;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepo;
    private final UserRepository userRepo;

    public VehicleService(VehicleRepository vehicleRepo, UserRepository userRepo) {
        this.vehicleRepo = vehicleRepo;
        this.userRepo    = userRepo;
    }

    public Vehicle registerVehicle(Long driverId, Vehicle vehicle) {
        User driver = userRepo.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        vehicle.setDriver(driver);
        vehicle.setApproved(false);
        return vehicleRepo.save(vehicle);
    }

    public List<Vehicle> getDriverVehicles(Long driverId) {
        return vehicleRepo.findByDriverId(driverId);
    }

    public List<Vehicle> getPendingVehicles() {
        return vehicleRepo.findAll().stream()
                .filter(v -> !v.isApproved())
                .toList();
    }

    public Vehicle approveVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        vehicle.setApproved(true);
        vehicle.setActive(true);
        return vehicleRepo.save(vehicle);
    }

    public Vehicle rejectVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        vehicle.setApproved(false);
        vehicle.setActive(false);
        return vehicleRepo.save(vehicle);
    }
}