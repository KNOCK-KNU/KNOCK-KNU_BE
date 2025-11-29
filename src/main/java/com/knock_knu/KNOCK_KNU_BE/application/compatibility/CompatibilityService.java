package com.knock_knu.KNOCK_KNU_BE.application.compatibility;

import com.knock_knu.KNOCK_KNU_BE.application.gemini.GeminiService;
import com.knock_knu.KNOCK_KNU_BE.presentation.compatibility.dto.CompatibiiltyResponse;
import com.knock_knu.KNOCK_KNU_BE.presentation.compatibility.dto.CompatibilityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompatibilityService {
    private final GeminiService geminiService;

    public CompatibiiltyResponse getCompatibility(CompatibilityRequest request) {
        return geminiService.getCompatibility(request);
    }
}
