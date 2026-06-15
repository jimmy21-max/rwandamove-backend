package rw.rwandamove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.rwandamove.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);
    Optional<User> findByEmail(String email);
    List<User> findByRole(User.Role role);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}