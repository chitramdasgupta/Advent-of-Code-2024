package org.example.day_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Two {
    private List<List<Long>> reports;

    public Two(String path) {
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

    public long safeReportsWithDampener() {
        long count = 0;

        for (List<Long> levels : reports) {
            if (isValidSequence(levels) || canBecomeValidByRemovingOneLevel(levels)) {
                ++count;
            }
        }

        return count;
    }

    // Check if a sequence is strictly increasing or decreasing within the allowed difference
    private boolean isValidSequence(List<Long> levels) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < levels.size(); i++) {
            long diff = levels.get(i) - levels.get(i - 1);

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                increasing = false;
                decreasing = false;
                break;
            }

            if (diff <= 0) increasing = false;
            if (diff >= 0) decreasing = false;
        }

        return increasing || decreasing;
    }

    // Check if removing any single level makes the sequence valid
    private boolean canBecomeValidByRemovingOneLevel(List<Long> levels) {
        for (int i = 0; i < levels.size(); i++) {
            List<Long> modifiedLevels = new ArrayList<>(levels);
            modifiedLevels.remove(i);

            if (isValidSequence(modifiedLevels)) {
                return true;
            }
        }

        return false;
    }
}

