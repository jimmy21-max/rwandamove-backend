package rw.rwandamove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.rwandamove.entity.Vehicle;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByDriverId(Long driverId);
    List<Vehicle> findByTypeAndIsApprovedAndIsActive(
            Vehicle.VehicleType type, boolean isApproved, boolean isActive);
}