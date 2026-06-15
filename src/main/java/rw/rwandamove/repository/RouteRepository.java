package rw.rwandamove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.rwandamove.entity.Route;
import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByIsActive(boolean isActive);
    List<Route> findByStartDistrictAndEndDistrict(String start, String end);
}