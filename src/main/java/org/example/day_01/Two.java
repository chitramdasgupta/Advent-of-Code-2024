package org.example.day_01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Two {
    private final List<Long> leftList;
    private final Map<Long, Long> rightListFrequency;

    public Two(String path) {
        leftList = new ArrayList<>();
        rightListFrequency = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] split = line.trim().split("\\s+");

                leftList.add(Long.parseLong(split[0]));

                Long val = Long.parseLong(split[1]);
                if (!rightListFrequency.containsKey(val)) {
                    rightListFrequency.put(val, 0L);
                }

                rightListFrequency.put(val, rightListFrequency.get(val) + 1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long similarityScore() {
        long score = 0;
        for (long num : leftList) {
            score += num * rightListFrequency.getOrDefault(num, 0L);
        }

        return score;
    }
}
