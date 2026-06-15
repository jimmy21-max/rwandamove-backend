package rw.rwandamove.service;

import org.springframework.stereotype.Service;
import rw.rwandamove.entity.RideRequest;
import rw.rwandamove.repository.RideRequestRepository;
import java.math.BigDecimal;
import java.util.List;

@Service
public class RideRequestService {

    private final RideRequestRepository rideRepo;
    private static final BigDecimal RATE_PER_KM = new BigDecimal("500");

    public RideRequestService(RideRequestRepository rideRepo) {
        this.rideRepo = rideRepo;
    }

    public RideRequest createRideRequest(RideRequest request) {
        return rideRepo.save(request);
    }

    public RideRequest acceptRide(Long rideId, Long driverId) {
        RideRequest ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        ride.setStatus(RideRequest.RideStatus.ACCEPTED);
        return rideRepo.save(ride);
    }

    public RideRequest completeRide(Long rideId, BigDecimal distanceKm) {
        RideRequest ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        ride.setDistanceKm(distanceKm);
        ride.setFareAmount(distanceKm.multiply(RATE_PER_KM));
        ride.setStatus(RideRequest.RideStatus.COMPLETED);
        return rideRepo.save(ride);
    }

    public List<RideRequest> getPendingRides() {
        return rideRepo.findByStatus(RideRequest.RideStatus.PENDING);
    }
}