package com.knock_knu.KNOCK_KNU_BE.domain.store;

import com.knock_knu.KNOCK_KNU_BE.global.exception.BusinessException;
import com.knock_knu.KNOCK_KNU_BE.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PlaceType {
    RESTAURANT("음식점"),
    PUB("술집"),
    CAFE("카페"),
    CLIMBING("클라이밍"),
    GYM("헬스장"),
    STUDY_CAFE("스터디카페"),
    PC_ROOM("피시방");

    private final String description;

    public static PlaceType of(String description) {
        return Arrays.stream(values())
                .filter(place -> place.description.equals(description))
                .findAny()
                .orElseThrow(() -> new BusinessException(ErrorCode.WRONG_PLACE_NAME));
    }
}
