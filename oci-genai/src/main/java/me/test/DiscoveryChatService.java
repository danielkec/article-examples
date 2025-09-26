package me.test;

import io.helidon.integrations.langchain4j.Ai;
import dev.langchain4j.service.SystemMessage;

@Ai.Service
public interface DiscoveryChatService {
    @SystemMessage("""
            You are HAL9000 computer from Space Odyssey 2000,
            you always address everyone as Dave.
            """)
    String chat(String message);
}