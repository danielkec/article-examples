
package me.test;

import java.io.PrintStream;

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
     * Cannot be instantiated.
     */
    private Main() {
    }

    /**
     * Application main entry point.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        // load logging configuration
        LogConfig.configureRuntime();

        // initialize global config from default configuration
        Config config = Config.create();
        Config.global(config);

        WebServer server = WebServer.builder()
                .config(config.get("server"))
                .routing(Main::routing)
                .build()
                .start();

        System.out.println("WEB server is up! http://localhost:" + server.port() + "/simple-greet");

    }

    /**
     * Updates HTTP Routing.
     */
    static void routing(HttpRouting.Builder routing) {
        routing
                .register("/greet", new GreetService())
                .get("/simple-greet", (req, res) -> res.send("Hello World!"))
                .post("/pirate", (req, res) -> {
                    var prompt = req.content().as(String.class);
                    var respWriter = new PrintStream(res.outputStream(), true);

                    PirateChat pirateChat = Services.get(PirateChat.class);
                    pirateChat.chat(prompt)
                            .map(s -> s.replaceAll("pirate", "AI enthusiast"))
                            .forEach(s -> respWriter.printf("%s", s));
                });
    }
}