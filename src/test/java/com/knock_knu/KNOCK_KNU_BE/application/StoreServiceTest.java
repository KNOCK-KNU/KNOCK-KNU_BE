package com.knock_knu.KNOCK_KNU_BE.application;

import com.knock_knu.KNOCK_KNU_BE.application.store.StoreService;
import com.knock_knu.KNOCK_KNU_BE.domain.store.*;
import com.knock_knu.KNOCK_KNU_BE.presentation.store.dto.StoreResponse;
import com.knock_knu.KNOCK_KNU_BE.global.exception.BusinessException;
import com.knock_knu.KNOCK_KNU_BE.global.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @Test
    @DisplayName("상점 단건 조회 - 성공")
    void getStore_Success() {
        // given (준비)
        Long storeId = 1L;
        Store store = new Store(
                "경대리아",
                "대구광역시 북구",
                "북문",
                "가성비인",
                35.123,
                128.123,
                "음식점"
        );

        given(storeRepository.findById(storeId)).willReturn(Optional.of(store));

        StoreResponse response = storeService.getStore(storeId);

        assertThat(response).isNotNull();
        assertThat(response.name()).isEqualTo("경대리아");
        assertThat(response.longitude()).isEqualTo(128.123);
        verify(storeRepository).findById(storeId);
    }

    @Test
    @DisplayName("상점 단건 조회 - 실패 (존재하지 않는 ID)")
    void getStore_Fail_NotFound() {
        // given
        Long invalidId = 999L;
        given(storeRepository.findById(invalidId)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> storeService.getStore(invalidId))
                .isInstanceOf(BusinessException.class)
                .hasFieldOrPropertyWithValue("code", "SE_004");
    }

    @Test
    @DisplayName("모든 상점 조회 - 성공")
    void getAllStores_Success() {
        // given
        Store store1 = new Store(
                "경대리아",
                "대구광역시 북구",
                "북문",
                "가성비인",
                35.123,
                128.123,
                "음식점"
        );

        Store store2 = new Store(
                "쪽문분식",
                "대구광역시 북구",
                "쪽문",
                "가성비인",
                35.999,
                128.999,
                "음식점"
        );
        given(storeRepository.findAll()).willReturn(List.of(store1, store2));

        // when
        List<StoreResponse> responses = storeService.getAllStores();

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).name()).isEqualTo("경대리아");
        assertThat(responses.get(1).name()).isEqualTo("쪽문분식");
    }
}
