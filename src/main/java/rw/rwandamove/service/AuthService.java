package rw.rwandamove.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rw.rwandamove.dto.AuthRequest;
import rw.rwandamove.dto.AuthResponse;
import rw.rwandamove.dto.RegisterRequest;
import rw.rwandamove.entity.User;
import rw.rwandamove.entity.Wallet;
import rw.rwandamove.repository.UserRepository;
import rw.rwandamove.repository.WalletRepository;
import rw.rwandamove.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository   userRepo;
    private final WalletRepository walletRepo;
    private final PasswordEncoder  encoder;
    private final JwtUtil          jwtUtil;
    private final SmsService       smsService;

    public AuthService(UserRepository userRepo,
                       WalletRepository walletRepo,
                       PasswordEncoder encoder,
                       JwtUtil jwtUtil,
                       SmsService smsService) {
        this.userRepo    = userRepo;
        this.walletRepo  = walletRepo;
        this.encoder     = encoder;
        this.jwtUtil     = jwtUtil;
        this.smsService  = smsService;
    }

    public AuthResponse register(RegisterRequest req) {
        if (userRepo.existsByPhone(req.getPhone()))
            throw new RuntimeException("Phone already registered");

        User user = new User();
        user.setFullName(req.getFullName());
        user.setPhone(req.getPhone());
        user.setEmail(req.getEmail());
        user.setPasswordHash(encoder.encode(req.getPassword()));
        user.setRole(req.getRole());
        user.setNationalId(req.getNationalId());
        user.setActive(true);
        user.setVerified(false);
        userRepo.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        walletRepo.save(wallet);

        try {
            smsService.sendWelcomeSms(user.getPhone(), user.getFullName());
        } catch (Exception e) {
            System.out.println("SMS not sent: " + e.getMessage());
        }

        String token = jwtUtil.generateToken(user.getPhone(), user.getRole().name());
        return new AuthResponse(token, user.getRole().name(), user.getId(), user.getFullName());
    }

    public AuthResponse login(AuthRequest req) {
        User user = userRepo.findByPhone(req.getPhone())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.getPassword(), user.getPasswordHash()))
            throw new RuntimeException("Invalid password");

        if (!user.isActive())
            throw new RuntimeException("Account suspended");

        String token = jwtUtil.generateToken(user.getPhone(), user.getRole().name());
        return new AuthResponse(token, user.getRole().name(), user.getId(), user.getFullName());
    }
}