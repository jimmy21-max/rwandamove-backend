package rw.rwandamove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.rwandamove.entity.Trip;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDriverId(Long driverId);
    List<Trip> findByRouteIdAndStatus(Long routeId, Trip.TripStatus status);
    List<Trip> findByStatus(Trip.TripStatus status);
}