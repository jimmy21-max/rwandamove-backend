package rw.rwandamove.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stations")
public class Station {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String district;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Province province;

    private BigDecimal latitude;
    private BigDecimal longitude;
    private String vehicleTypesServed;
    private boolean isActive = true;

    public enum Province { KIGALI, NORTHERN, SOUTHERN, EASTERN, WESTERN }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public Province getProvince() { return province; }
    public void setProvince(Province province) { this.province = province; }
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
    public String getVehicleTypesServed() { return vehicleTypesServed; }
    public void setVehicleTypesServed(String vehicleTypesServed) { this.vehicleTypesServed = vehicleTypesServed; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
}