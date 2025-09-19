package me.test;

import io.helidon.logging.common.LogConfig;
import io.helidon.config.Config;
import io.helidon.service.registry.Services;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;

/**
 * The application main class.
 */
public class Main {

    /**
     * Application main entry point.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // load logging configuration
        LogConfig.configureRuntime();

        // initialize global config from default configuration
        Config config = Services.get(Config.class);

        WebServer server = WebServer.builder()
                .config(config.get("server"))
                .routing(Main::routing)
                .build()
                .start();

        System.out.println("WEB server is up! http://localhost:" + server.port() + "/chat");
    }

    /**
     * Updates HTTP Routing.
     */
    static void routing(HttpRouting.Builder routing) {
        routing
                .post("/chat", (req, res) -> {
                    var prompt = req.content().as(String.class);
                    var response = Services.get(SpaceOdysseyChat.class)
                            .chat(prompt);
                    res.send(response);
                });
    }
}