package rw.rwandamove.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;

    @Column(nullable = false, unique = true)
    private String plateNumber;

    private String model;
    private String brand;
    private String color;
    private Integer year;

    @Column(nullable = false)
    private Integer capacity;

    @Column(columnDefinition = "TEXT")
    private String vehiclePhotos;

    private LocalDate insuranceExpiry;
    private LocalDate registrationExpiry;
    private String motoPermit;
    private String taxiPermit;
    private String busRoutePermit;
    private boolean isApproved = false;
    private boolean isActive = true;
    private BigDecimal currentLat;
    private BigDecimal currentLng;
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum VehicleType { BUS, TAXI_CAR, MOTORCYCLE }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getDriver() { return driver; }
    public void setDriver(User driver) { this.driver = driver; }
    public VehicleType getType() { return type; }
    public void setType(VehicleType type) { this.type = type; }
    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public String getVehiclePhotos() { return vehiclePhotos; }
    public void setVehiclePhotos(String vehiclePhotos) { this.vehiclePhotos = vehiclePhotos; }
    public LocalDate getInsuranceExpiry() { return insuranceExpiry; }
    public void setInsuranceExpiry(LocalDate insuranceExpiry) { this.insuranceExpiry = insuranceExpiry; }
    public LocalDate getRegistrationExpiry() { return registrationExpiry; }
    public void setRegistrationExpiry(LocalDate registrationExpiry) { this.registrationExpiry = registrationExpiry; }
    public String getMotoPermit() { return motoPermit; }
    public void setMotoPermit(String motoPermit) { this.motoPermit = motoPermit; }
    public String getTaxiPermit() { return taxiPermit; }
    public void setTaxiPermit(String taxiPermit) { this.taxiPermit = taxiPermit; }
    public String getBusRoutePermit() { return busRoutePermit; }
    public void setBusRoutePermit(String busRoutePermit) { this.busRoutePermit = busRoutePermit; }
    public boolean isApproved() { return isApproved; }
    public void setApproved(boolean isApproved) { this.isApproved = isApproved; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
    public BigDecimal getCurrentLat() { return currentLat; }
    public void setCurrentLat(BigDecimal currentLat) { this.currentLat = currentLat; }
    public BigDecimal getCurrentLng() { return currentLng; }
    public void setCurrentLng(BigDecimal currentLng) { this.currentLng = currentLng; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}