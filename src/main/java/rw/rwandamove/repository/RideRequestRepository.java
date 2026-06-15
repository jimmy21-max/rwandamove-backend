package rw.rwandamove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.rwandamove.entity.RideRequest;
import java.util.List;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
    List<RideRequest> findByPassengerId(Long passengerId);
    List<RideRequest> findByMotoDriverId(Long driverId);
    List<RideRequest> findByStatus(RideRequest.RideStatus status);
}