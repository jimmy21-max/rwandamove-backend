package rw.rwandamove.service;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class GpsService {

    private final SocketIOServer server;

    public GpsService(SocketIOServer server) {
        this.server = server;
    }

    @PostConstruct
    public void start() {
        server.addConnectListener(client -> {
            System.out.println("Driver connected: " + client.getSessionId());
        });

        server.addDisconnectListener(client -> {
            System.out.println("Driver disconnected: " + client.getSessionId());
        });

        server.addEventListener("driver_location", Map.class, (client, data, ack) -> {
            System.out.println("GPS update received: " + data);
            server.getBroadcastOperations().sendEvent("location_update", data);
        });

        server.start();
        System.out.println("✅ Socket.IO GPS server started on port 9092");
    }

    @PreDestroy
    public void stop() {
        server.stop();
    }
}