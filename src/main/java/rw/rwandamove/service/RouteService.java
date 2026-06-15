package rw.rwandamove.service;

import org.springframework.stereotype.Service;
import rw.rwandamove.entity.Route;
import rw.rwandamove.repository.RouteRepository;
import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepo;

    public RouteService(RouteRepository routeRepo) {
        this.routeRepo = routeRepo;
    }

    public List<Route> getAllRoutes() {
        return routeRepo.findByIsActive(true);
    }

    public Route getRoute(Long id) {
        return routeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }

    public Route createRoute(Route route) {
        return routeRepo.save(route);
    }
}