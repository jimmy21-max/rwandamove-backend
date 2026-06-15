package rw.rwandamove.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Enumerated(EnumType.STRING)
    private Vehicle.VehicleType vehicleType;

    @Column(nullable = false, unique = true)
    private String ticketCode;

    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "pickup_station_id")
    private Station pickupStation;

    @ManyToOne
    @JoinColumn(name = "dropoff_station_id")
    private Station dropoffStation;

    @Column(nullable = false)
    private BigDecimal fareAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus = BookingStatus.CONFIRMED;

    @Column(columnDefinition = "TEXT")
    private String qrCode;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum PaymentStatus  { PENDING, PAID, REFUNDED }
    public enum PaymentMethod  { MTN_MOMO, AIRTEL_MONEY, WALLET, CASH }
    public enum BookingStatus  { CONFIRMED, CANCELLED, COMPLETED }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getPassenger() { return passenger; }
    public void setPassenger(User passenger) { this.passenger = passenger; }
    public Trip getTrip() { return trip; }
    public void setTrip(Trip trip) { this.trip = trip; }
    public Vehicle.VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(Vehicle.VehicleType vehicleType) { this.vehicleType = vehicleType; }
    public String getTicketCode() { return ticketCode; }
    public void setTicketCode(String ticketCode) { this.ticketCode = ticketCode; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public Station getPickupStation() { return pickupStation; }
    public void setPickupStation(Station pickupStation) { this.pickupStation = pickupStation; }
    public Station getDropoffStation() { return dropoffStation; }
    public void setDropoffStation(Station dropoffStation) { this.dropoffStation = dropoffStation; }
    public BigDecimal getFareAmount() { return fareAmount; }
    public void setFareAmount(BigDecimal fareAmount) { this.fareAmount = fareAmount; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public BookingStatus getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(BookingStatus bookingStatus) { this.bookingStatus = bookingStatus; }
    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}