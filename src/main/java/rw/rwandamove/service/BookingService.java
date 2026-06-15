package rw.rwandamove.service;

import org.springframework.stereotype.Service;
import rw.rwandamove.entity.Booking;
import rw.rwandamove.entity.Trip;
import rw.rwandamove.entity.User;
import rw.rwandamove.repository.BookingRepository;
import rw.rwandamove.repository.TripRepository;
import rw.rwandamove.repository.UserRepository;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final TripRepository    tripRepo;
    private final UserRepository    userRepo;
    private final SmsService        smsService;

    public BookingService(BookingRepository bookingRepo,
                          TripRepository tripRepo,
                          UserRepository userRepo,
                          SmsService smsService) {
        this.bookingRepo = bookingRepo;
        this.tripRepo    = tripRepo;
        this.userRepo    = userRepo;
        this.smsService  = smsService;
    }

    public Booking createBooking(Booking booking) {
        Trip trip = tripRepo.findById(booking.getTrip().getId())
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (trip.getAvailableSeats() <= 0)
            throw new RuntimeException("No seats available");

        String ticketCode = "TK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        booking.setTicketCode(ticketCode);
        trip.setAvailableSeats(trip.getAvailableSeats() - 1);
        tripRepo.save(trip);

        Booking saved = bookingRepo.save(booking);

        try {
            User passenger = userRepo.findById(booking.getPassenger().getId()).orElse(null);
            if (passenger != null) {
                String route = trip.getRoute() != null ? trip.getRoute().getName() : "Your trip";
                smsService.sendBookingConfirmation(
                    passenger.getPhone(),
                    passenger.getFullName(),
                    ticketCode,
                    route,
                    booking.getFareAmount().toString()
                );
            }
        } catch (Exception e) {
            System.out.println("Booking SMS not sent: " + e.getMessage());
        }

        return saved;
    }

    public List<Booking> getPassengerBookings(Long passengerId) {
        return bookingRepo.findByPassengerId(passengerId);
    }

    public List<Booking> getTripPassengers(Long tripId) {
        return bookingRepo.findByTripId(tripId);
    }

    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setBookingStatus(Booking.BookingStatus.CANCELLED);
        booking.setPaymentStatus(Booking.PaymentStatus.REFUNDED);
        Trip trip = booking.getTrip();
        trip.setAvailableSeats(trip.getAvailableSeats() + 1);
        tripRepo.save(trip);
        return bookingRepo.save(booking);
    }
}