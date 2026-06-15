package rw.rwandamove.service;

import org.springframework.stereotype.Service;
import rw.rwandamove.entity.Trip;
import rw.rwandamove.repository.TripRepository;
import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepo;

    public TripService(TripRepository tripRepo) {
        this.tripRepo = tripRepo;
    }

    public Trip createTrip(Trip trip) {
        trip.setStatus(Trip.TripStatus.ACTIVE);
        return tripRepo.save(trip);
    }

    public List<Trip> getActiveTrips() {
        return tripRepo.findByStatus(Trip.TripStatus.ACTIVE);
    }

    public List<Trip> getAllTrips() {
        return tripRepo.findAll();
    }

    public List<Trip> getTripsByDriver(Long driverId) {
        return tripRepo.findByDriverId(driverId);
    }

    public Trip startTrip(Long tripId) {
        Trip trip = tripRepo.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.setStatus(Trip.TripStatus.ACTIVE);
        return tripRepo.save(trip);
    }

    public Trip completeTrip(Long tripId) {
        Trip trip = tripRepo.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.setStatus(Trip.TripStatus.COMPLETED);
        return tripRepo.save(trip);
    }

    public Trip cancelTrip(Long tripId) {
        Trip trip = tripRepo.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.setStatus(Trip.TripStatus.CANCELLED);
        return tripRepo.save(trip);
    }
}