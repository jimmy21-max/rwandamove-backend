package rw.rwandamove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.rwandamove.entity.Station;
import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findByProvince(Station.Province province);
    List<Station> findByDistrict(String district);
}