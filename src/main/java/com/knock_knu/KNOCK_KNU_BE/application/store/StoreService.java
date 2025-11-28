package com.knock_knu.KNOCK_KNU_BE.application.store;

import com.knock_knu.KNOCK_KNU_BE.domain.store.Store;
import com.knock_knu.KNOCK_KNU_BE.domain.store.StoreRepository;
import com.knock_knu.KNOCK_KNU_BE.global.exception.BusinessException;
import com.knock_knu.KNOCK_KNU_BE.global.exception.ErrorCode;
import com.knock_knu.KNOCK_KNU_BE.presentation.store.dto.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public StoreResponse getStore(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.STORE_NOT_FOUND));
        return StoreResponse.from(store);
    }

    @Transactional(readOnly = true)
    public List<StoreResponse> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream().map(StoreResponse::from).toList();
    }
}
