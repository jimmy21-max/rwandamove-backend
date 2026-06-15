package rw.rwandamove.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.rwandamove.entity.User;
import rw.rwandamove.service.AdminService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<User>> allDrivers() {
        return ResponseEntity.ok(adminService.getDrivers());
    }

    @GetMapping("/passengers")
    public ResponseEntity<List<User>> allPassengers() {
        return ResponseEntity.ok(adminService.getPassengers());
    }

    @PutMapping("/users/{id}/activate")
    public ResponseEntity<User> activate(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.activateUser(id));
    }

    @PutMapping("/users/{id}/suspend")
    public ResponseEntity<User> suspend(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.suspendUser(id));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> stats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalUsers",     adminService.countUsers());
        stats.put("totalDrivers",   adminService.countDrivers());
        stats.put("totalPassengers",adminService.countPassengers());
        stats.put("totalBookings",  adminService.countBookings());
        return ResponseEntity.ok(stats);
    }
}