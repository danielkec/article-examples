package me.test;

import java.util.Set;
import io.helidon.integrations.langchain4j.AiProvider;
import dev.langchain4j.model.mistralai.MistralAiChatModel;
import io.helidon.builder.api.Option;
import dev.langchain4j.model.chat.Capability;

@AiProvider.ModelConfig(MistralAiChatModel.class)
interface MistralAiLc4jProvider {

    @Option.Configured
    Set<Capability> supportedCapabilities();

}