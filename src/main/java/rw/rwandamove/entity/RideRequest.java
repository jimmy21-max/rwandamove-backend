package rw.rwandamove.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ride_requests")
public class RideRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @ManyToOne
    @JoinColumn(name = "moto_driver_id")
    private User motoDriver;

    @Column(nullable = false) private BigDecimal pickupLat;
    @Column(nullable = false) private BigDecimal pickupLng;
    private String pickupAddress;
    @Column(nullable = false) private BigDecimal dropoffLat;
    @Column(nullable = false) private BigDecimal dropoffLng;
    private String dropoffAddress;

    @Enumerated(EnumType.STRING)
    private RideStatus status = RideStatus.PENDING;

    private BigDecimal distanceKm;
    private BigDecimal fareAmount;

    @Enumerated(EnumType.STRING)
    private Booking.PaymentStatus paymentStatus = Booking.PaymentStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private Booking.PaymentMethod paymentMethod;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum RideStatus { PENDING, ACCEPTED, STARTED, COMPLETED, CANCELLED }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getPassenger() { return passenger; }
    public void setPassenger(User passenger) { this.passenger = passenger; }
    public User getMotoDriver() { return motoDriver; }
    public void setMotoDriver(User motoDriver) { this.motoDriver = motoDriver; }
    public BigDecimal getPickupLat() { return pickupLat; }
    public void setPickupLat(BigDecimal pickupLat) { this.pickupLat = pickupLat; }
    public BigDecimal getPickupLng() { return pickupLng; }
    public void setPickupLng(BigDecimal pickupLng) { this.pickupLng = pickupLng; }
    public String getPickupAddress() { return pickupAddress; }
    public void setPickupAddress(String pickupAddress) { this.pickupAddress = pickupAddress; }
    public BigDecimal getDropoffLat() { return dropoffLat; }
    public void setDropoffLat(BigDecimal dropoffLat) { this.dropoffLat = dropoffLat; }
    public BigDecimal getDropoffLng() { return dropoffLng; }
    public void setDropoffLng(BigDecimal dropoffLng) { this.dropoffLng = dropoffLng; }
    public String getDropoffAddress() { return dropoffAddress; }
    public void setDropoffAddress(String dropoffAddress) { this.dropoffAddress = dropoffAddress; }
    public RideStatus getStatus() { return status; }
    public void setStatus(RideStatus status) { this.status = status; }
    public BigDecimal getDistanceKm() { return distanceKm; }
    public void setDistanceKm(BigDecimal distanceKm) { this.distanceKm = distanceKm; }
    public BigDecimal getFareAmount() { return fareAmount; }
    public void setFareAmount(BigDecimal fareAmount) { this.fareAmount = fareAmount; }
    public Booking.PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(Booking.PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
    public Booking.PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(Booking.PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}