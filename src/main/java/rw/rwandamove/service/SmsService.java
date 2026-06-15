package rw.rwandamove.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class SmsService {

    @Value("${africastalking.username}")
    private String username;

    @Value("${africastalking.api-key}")
    private String apiKey;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void sendSms(String phone, String message) {
        try {
            String formattedPhone = "+250" + phone.replaceAll("^0", "");

            String body = "username=" + URLEncoder.encode(username, StandardCharsets.UTF_8)
                    + "&to=" + URLEncoder.encode(formattedPhone, StandardCharsets.UTF_8)
                    + "&message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.sandbox.africastalking.com/version1/messaging"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("apiKey", apiKey)
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("✅ SMS sent to " + formattedPhone + " | Response: " + response.body());

        } catch (Exception e) {
            System.out.println("❌ SMS failed: " + e.getMessage());
        }
    }

    public void sendBookingConfirmation(String phone, String fullName,
                                        String ticketCode, String route, String fareAmount) {
        String message = "Dear " + fullName + ",\n"
                + "RwandaMove booking confirmed!\n"
                + "Ticket: " + ticketCode + "\n"
                + "Route: " + route + "\n"
                + "Fare: " + fareAmount + " RWF\n"
                + "Safe journey!";
        sendSms(phone, message);
    }

    public void sendRideAccepted(String phone, String driverName) {
        String message = "Your moto ride was accepted by " + driverName + ".\n"
                + "They are heading to your pickup location.\n"
                + "RwandaMove";
        sendSms(phone, message);
    }

    public void sendWelcomeSms(String phone, String fullName) {
        String message = "Welcome to RwandaMove, " + fullName + "!\n"
                + "Your account is ready.\n"
                + "Book buses, taxis and motos across Rwanda.\n"
                + "RwandaMove Team";
        sendSms(phone, message);
    }
}