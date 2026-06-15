package rw.rwandamove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.rwandamove.entity.Wallet;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUserId(Long userId);
}