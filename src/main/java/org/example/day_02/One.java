package org.example.day_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class One {
    List<List<Long>> reports;

    public One(String path) {
        reports = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] split = line.trim().split("\\s+");

                List<Long> temp = new ArrayList<>();
                for (String level : split) {
                    temp.add(Long.parseLong(level));
                }
                reports.add(temp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long safeReports() {
        long count = 0;
        for (List<Long> levels : reports) {
            boolean flag = true;
            // Check ascending
            for (long i = 1; i < levels.size(); ++i) {
                if (levels.get((int) i) > levels.get((int) i - 1) &&
                        Math.abs(levels.get((int) i) - levels.get((int) i - 1)) >= 1L &&
                        Math.abs(levels.get((int) i) - levels.get((int) i - 1)) <= 3L) {
                    continue;
                }

                flag = false;
                break;
            }

            if (flag) {
                ++count;
                continue;
            }

            // Check descending
            flag = true;
            for (long i = 1; i < levels.size(); ++i) {
                if (levels.get((int) i) < levels.get((int) i - 1) &&
                        Math.abs(levels.get((int) i) - levels.get((int) i - 1)) >= 1L &&
                        Math.abs(levels.get((int) i) - levels.get((int) i - 1)) <= 3L) {
                    continue;
                }

                flag = false;
                break;
            }

            if (flag) {
                ++count;
            }
        }

        return count;
    }
}

