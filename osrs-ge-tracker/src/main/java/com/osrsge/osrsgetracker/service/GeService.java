package com.osrsge.osrsgetracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class GeService {
    private static final String OSRS_API_URL = "https://prices.runescape.wiki/api/v1/osrs/latest";

    // Hoidla kasutaja poolt lisatud itemitele
    private final Map<String, Integer> customItems = new LinkedHashMap<>();

    public GeService() {
        // Lisa vaikimisi mõned itemid
        customItems.put("Raw Shark", 383);
        customItems.put("Zulrah's Scales", 12934);
        customItems.put("Dragon Bones", 536);
        customItems.put("Magic Logs", 1513);
    }

    public Map<String, Map<String, Object>> getAllPrices() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(OSRS_API_URL, Map.class);

        if (response != null && response.containsKey("data")) {
            Map<String, Object> data = (Map<String, Object>) response.get("data");
            Map<String, Map<String, Object>> results = new LinkedHashMap<>();

            for (Map.Entry<String, Integer> entry : customItems.entrySet()) {
                if (data.containsKey(String.valueOf(entry.getValue()))) {
                    results.put(entry.getKey(), (Map<String, Object>) data.get(String.valueOf(entry.getValue())));
                }
            }
            return results;
        }
        return new HashMap<>();
    }

    // Lisa uus item kasutaja sisestatud väärtustega
    public void addItem(String name, int id) {
        customItems.put(name, id);
    }
}
