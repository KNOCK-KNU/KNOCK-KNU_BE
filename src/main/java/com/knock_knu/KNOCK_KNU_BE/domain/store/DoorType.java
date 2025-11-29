package com.knock_knu.KNOCK_KNU_BE.domain.store;

import com.knock_knu.KNOCK_KNU_BE.global.exception.BusinessException;
import com.knock_knu.KNOCK_KNU_BE.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum DoorType {
    ON_CAMPUS("교내"),
    SIDE("쪽문"),
    MAIN("정문"),
    EAST("동문"),
    NORTH("북문"),
    WEST("서문"),
    SOLO("솔로문"),
    TECHNO("테크노문");

    private final String description;

    public static DoorType of(String description) {
        return Arrays.stream(values())
                .filter(door -> door.description.equals(description))
                .findAny()
                .orElseThrow(() -> new BusinessException(ErrorCode.WRONG_DOOR_NAME));
    }

    public static List<String> getAllDescriptions() {
        return Arrays.stream(values())
                .map(DoorType::getDescription)
                .toList();
    }
}
