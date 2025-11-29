package com.knock_knu.KNOCK_KNU_BE.application.gemini;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knock_knu.KNOCK_KNU_BE.application.gemini.dto.GeminiRequest;
import com.knock_knu.KNOCK_KNU_BE.application.gemini.dto.GeminiResponse;
import com.knock_knu.KNOCK_KNU_BE.global.exception.BusinessException;
import com.knock_knu.KNOCK_KNU_BE.global.exception.ErrorCode;
import com.knock_knu.KNOCK_KNU_BE.presentation.fortune.dto.FortuneRequest;
import com.knock_knu.KNOCK_KNU_BE.presentation.fortune.dto.FortuneResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeminiService {
    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();
    private final ObjectMapper objectMapper;

    private static final String FORTUNE_PROMPT = """
            너는 사주명리학과 MBTI 심리학을 결합한 신세대 점쟁이야.
            아래 사용자의 정보를 바탕으로 오늘의 운세를 봐줘.
            
            [사용자 정보]
            이름: %s
            생년월일: %s
            성별 : %s
            MBTI: %s
            
            [지시사항]
            1. 운세를 풀이할 때 반드시 사용자의 MBTI 성향(내향/외향, 이성/감성 등)과 연결 지어서 설명해.
               예) "ISFJ인 당신은 평소 남을 잘 챙기지만, 오늘은..." 또는 "ENTP인 당신의 톡톡 튀는 아이디어가 오늘은..."
            2. 조언은 MBTI의 약점을 보완하거나 강점을 살리는 방향으로 해줘.
            3. 답변은 무조건 아래의 JSON 형식으로만 작성해. (Markdown 코드 블럭 ```json 쓰지 마)
            4. score는 0에서 100 사이의 숫자로 줘.
            
            [응답 예시]
            {
                "title": "MBTI 맞춤형 한 줄 제목",
                "grandFortune": "전체적인 총운 (MBTI 성향 언급 필수)",
                "loveFortune": "애정운 (성격에 따른 연애 조언)",
                "wealthFortune": "재물운",
                "studyFortune": "학업/직업운",
                "score": 85,
                "luckyItem": "성향에 맞는 행운템"
            }
            """;

    public FortuneResponse getTodayFortune(FortuneRequest request){
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
        String prompt = String.format(FORTUNE_PROMPT,
                request.name(), request.birth(), request.gender(), request.mbti());

        GeminiRequest geminiRequest = new GeminiRequest(
                List.of(new GeminiRequest.Content(
                        List.of(new GeminiRequest.Part(prompt))
                ))
        );

        GeminiResponse response = restClient.post()
                .uri(url)
                .header("X-goog-api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(geminiRequest)
                .retrieve()
                .body(GeminiResponse.class);

        try {
            if (response != null && !response.candidates().isEmpty()) {
                String textResponse = response.candidates().get(0).content().parts().get(0).text();
                String cleanJson = textResponse.replace("```json", "").replace("```", "").trim();
                return objectMapper.readValue(cleanJson, FortuneResponse.class);
            }
        } catch (Exception e) {
            log.error("Gemini 응답 파싱 실패", e);
            throw new BusinessException(ErrorCode.FORTUNE_PARSING_ERROR);
        }

        throw new BusinessException(ErrorCode.GEMINI_API_ERROR);
    }
}
