package com.knock_knu.KNOCK_KNU_BE.domain.store;

import com.knock_knu.KNOCK_KNU_BE.global.exception.BusinessException;
import com.knock_knu.KNOCK_KNU_BE.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ModifierType {
    CROWDED("사람이 많은"),
    COST_EFFECTIVE("가성비인"),
    LARGE_PORTION("양이 많은"),
    SPACIOUS("넓은"),
    QUIET("조용한"),
    UNIQUE("이색적인"),
    OPEN_24_HOURS("24시간 하는"),
    LAPTOP_FRIENDLY("노트북석이 있는"),
    BUFFET("뷔페식인"),
    DUPLEX("복층인"),
    HAS_CAT("고양이가 있는"),
    DELIVERY_ONLY("배달전문인"),
    COZY("아늑한"),
    UNLIMITED_REFILL("무한리필인");

    private final String description;

    public static ModifierType of(String description) {
        return Arrays.stream(values())
                .filter(modifier -> modifier.description.equals(description))
                .findAny()
                .orElseThrow(() -> new BusinessException(ErrorCode.WRONG_MODIFIER_NAME));
    }

    public static List<String> getAllDescriptions() {
        return Arrays.stream(values())
                .map(ModifierType::getDescription)
                .toList();
    }
}