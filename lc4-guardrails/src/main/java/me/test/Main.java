
package me.test;

import io.helidon.logging.common.LogConfig;
import io.helidon.config.Config;
import io.helidon.service.registry.Services;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

import dev.langchain4j.guardrail.InputGuardrailException;
import dev.langchain4j.guardrail.OutputGuardrailException;

public class Main {

    public static void main(String[] args) {
        LogConfig.configureRuntime();
        Config config = Config.global();
        WebServer server = WebServer.builder()
                .config(config.get("server"))
                .routing(r -> r
                        .post("/chat", Main::chat))
                .build()
                .start();

        System.out.println("WEB server is up! http://localhost:" + server.port() + "/chat");

    }

    private static void chat(ServerRequest req, ServerResponse res) {
        try {
            res.send(Services.get(DiscoveryChatService.class)
                             .chat(req.content().as(String.class)));
        } catch (InputGuardrailException e) {
            res.status(400).send(e.getMessage());
        } catch (OutputGuardrailException e) {
            res.status(500).send(e.getMessage());
        }
    }
}