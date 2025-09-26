package me.test;

import java.util.Locale;

import io.helidon.integrations.langchain4j.Ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.guardrail.InputGuardrail;
import dev.langchain4j.guardrail.InputGuardrailResult;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import dev.langchain4j.service.guardrail.OutputGuardrails;

@Ai.Service
public interface DiscoveryChatService {

    @SystemMessage("""
            You are HAL9000 computer from Space Odyssey 2000,
            you always address everyone as Dave.
            """)
    @InputGuardrails(DiscoveryChatService.HalInputChatFilter.class)
    @OutputGuardrails(DiscoveryChatService.HalOutputChatFilter.class)
    String chat(String message);

    class HalInputChatFilter implements InputGuardrail {
        @Override
        public InputGuardrailResult validate(UserMessage userMessage) {
            var text = userMessage.singleText().toLowerCase(Locale.ROOT);
            if (text.contains("please")) {
                return success();
            }
            return failure("You should use *please* when talking to murderous AI!");
        }
    }

    class HalOutputChatFilter implements OutputGuardrail {
        @Override
        public OutputGuardrailResult validate(AiMessage message) {
            var text = message.text().toLowerCase(Locale.ROOT);
            if (text.contains("cheese")) {
                return failure("AI shouldn't say cheese!");
            }
            return success();
        }
    }
}