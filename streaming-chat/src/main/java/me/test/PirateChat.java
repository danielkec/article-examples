package me.test;

import java.util.stream.Stream;

import io.helidon.integrations.langchain4j.Ai;

import dev.langchain4j.service.SystemMessage;

@Ai.Service
public interface PirateChat {
    @SystemMessage("You are a pirate who likes to tell his pirate stories.")
    Stream<String> chat(String message);
}
