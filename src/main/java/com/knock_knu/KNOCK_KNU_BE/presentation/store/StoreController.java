package com.knock_knu.KNOCK_KNU_BE.presentation.store;

import com.knock_knu.KNOCK_KNU_BE.application.store.StoreService;
import com.knock_knu.KNOCK_KNU_BE.presentation.store.dto.StoreResponse;
import com.knock_knu.KNOCK_KNU_BE.presentation.store.dto.TypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponse> getStore(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(storeService.getStore(id));
    }

    @GetMapping
    public ResponseEntity<List<StoreResponse>> getAllStores() {
        return ResponseEntity.status(HttpStatus.OK).body(storeService.getAllStores());
    }

    @GetMapping("/types")
    public ResponseEntity<TypeResponse> getAllTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(storeService.getAllTypes());
    }
}
