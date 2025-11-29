package com.knock_knu.KNOCK_KNU_BE.application.gemini.dto;

import java.util.List;

public record GeminiRequest(List<Content> contents) {
    public record Content(List<Part> parts) {
    }

    public record Part(String text) {
    }
}