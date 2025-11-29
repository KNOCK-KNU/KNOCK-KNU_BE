package com.knock_knu.KNOCK_KNU_BE.global.init;

import com.knock_knu.KNOCK_KNU_BE.domain.store.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StoreDataLoader implements CommandLineRunner {

    private final StoreRepository storeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (storeRepository.count() > 0) {
            log.info("이미 상점 데이터가 존재하여 초기화를 건너뜁니다.");
            return;
        }

        log.info("초기 상점 데이터 적재 시작...");

        ClassPathResource resource = new ClassPathResource("stores.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            List<Store> storeList = new ArrayList<>();
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                try {
                    String name = data[0].trim();
                    String address = data[1].trim();
                    String doorStr = data[2].trim();
                    String modifierStr = data[3].trim();
                    double latitude = Double.parseDouble(data[4].trim());
                    double longitude = Double.parseDouble(data[5].trim());
                    String categoryStr = data[6].trim();

                    Store store = new Store(name, address, doorStr, modifierStr, latitude, longitude, categoryStr);

                    storeList.add(store);

                } catch (Exception e) {
                    log.error("데이터 파싱 중 오류 발생 (행: {}): {}", line, e.getMessage());
                }
            }

            storeRepository.saveAll(storeList);
            log.info("총 {}개의 상점 데이터 적재 완료!", storeList.size());
        }
    }
}
