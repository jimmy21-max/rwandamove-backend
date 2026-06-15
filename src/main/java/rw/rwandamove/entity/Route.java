package rw.rwandamove.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "routes")
public class Route {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String startDistrict;
    private String endDistrict;

    @ManyToOne
    @JoinColumn(name = "start_station_id")
    private Station startStation;

    @ManyToOne
    @JoinColumn(name = "end_station_id")
    private Station endStation;

    private BigDecimal distanceKm;
    private BigDecimal baseFareBus;
    private BigDecimal baseFareTaxi;
    private BigDecimal baseFareMoto;
    private Integer estimatedDurationMins;
    private boolean isActive = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStartDistrict() { return startDistrict; }
    public void setStartDistrict(String startDistrict) { this.startDistrict = startDistrict; }
    public String getEndDistrict() { return endDistrict; }
    public void setEndDistrict(String endDistrict) { this.endDistrict = endDistrict; }
    public Station getStartStation() { return startStation; }
    public void setStartStation(Station startStation) { this.startStation = startStation; }
    public Station getEndStation() { return endStation; }
    public void setEndStation(Station endStation) { this.endStation = endStation; }
    public BigDecimal getDistanceKm() { return distanceKm; }
    public void setDistanceKm(BigDecimal distanceKm) { this.distanceKm = distanceKm; }
    public BigDecimal getBaseFareBus() { return baseFareBus; }
    public void setBaseFareBus(BigDecimal baseFareBus) { this.baseFareBus = baseFareBus; }
    public BigDecimal getBaseFareTaxi() { return baseFareTaxi; }
    public void setBaseFareTaxi(BigDecimal baseFareTaxi) { this.baseFareTaxi = baseFareTaxi; }
    public BigDecimal getBaseFareMoto() { return baseFareMoto; }
    public void setBaseFareMoto(BigDecimal baseFareMoto) { this.baseFareMoto = baseFareMoto; }
    public Integer getEstimatedDurationMins() { return estimatedDurationMins; }
    public void setEstimatedDurationMins(Integer estimatedDurationMins) { this.estimatedDurationMins = estimatedDurationMins; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
}