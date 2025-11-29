package com.knock_knu.KNOCK_KNU_BE.presentation.compatibility;

import com.knock_knu.KNOCK_KNU_BE.application.compatibility.CompatibilityService;
import com.knock_knu.KNOCK_KNU_BE.presentation.compatibility.dto.CompatibiiltyResponse;
import com.knock_knu.KNOCK_KNU_BE.presentation.compatibility.dto.CompatibilityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CompatibilityController {
    private final CompatibilityService compatibilityService;

    @PostMapping("/mbticomb")
    public ResponseEntity<CompatibiiltyResponse> getCompatibility(@RequestBody CompatibilityRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(compatibilityService.getCompatibility(request));
    }
}
