package rw.rwandamove.service;

import org.springframework.stereotype.Service;
import rw.rwandamove.entity.User;
import rw.rwandamove.repository.UserRepository;
import rw.rwandamove.repository.VehicleRepository;
import rw.rwandamove.repository.BookingRepository;
import rw.rwandamove.repository.RideRequestRepository;
import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepo;
    private final VehicleRepository vehicleRepo;
    private final BookingRepository bookingRepo;
    private final RideRequestRepository rideRepo;

    public AdminService(UserRepository userRepo,
                        VehicleRepository vehicleRepo,
                        BookingRepository bookingRepo,
                        RideRequestRepository rideRepo) {
        this.userRepo    = userRepo;
        this.vehicleRepo = vehicleRepo;
        this.bookingRepo = bookingRepo;
        this.rideRepo    = rideRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public List<User> getDrivers() {
        return userRepo.findAll().stream()
                .filter(u -> u.getRole() == User.Role.BUS_DRIVER
                          || u.getRole() == User.Role.TAXI_DRIVER
                          || u.getRole() == User.Role.MOTO_DRIVER)
                .toList();
    }

    public List<User> getPassengers() {
        return userRepo.findByRole(User.Role.PASSENGER);
    }

    public User activateUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(true);
        return userRepo.save(user);
    }

    public User suspendUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        return userRepo.save(user);
    }

    public long countUsers() {
        return userRepo.count();
    }

    public long countDrivers() {
        return getDrivers().size();
    }

    public long countPassengers() {
        return userRepo.findByRole(User.Role.PASSENGER).size();
    }

    public long countBookings() {
        return bookingRepo.count();
    }
}