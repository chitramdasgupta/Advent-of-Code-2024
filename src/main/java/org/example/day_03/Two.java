package org.example.day_03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Two {
    private final List<String[]> matches;

    public Two(String path) {
        matches = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String regex = "(mul\\((\\d+),(\\d+)\\))|(do\\(\\))|(don't\\(\\))";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    if (matcher.group(2) != null && matcher.group(3) != null) {
                        // Match for mul(digits,digits), capture the digits
                        matches.add(new String[]{matcher.group(2), matcher.group(3)});
                    } else if (matcher.group(4) != null) {
                        // Match for do()
                        matches.add(new String[]{"do()"});
                    } else if (matcher.group(5) != null) {
                        // Match for don't()
                        matches.add(new String[]{"don't()"});
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int solve() {
        AtomicBoolean ignore = new AtomicBoolean(false);

        return matches.stream()
                .filter(entry -> {
                    if (entry.length == 1) {
                        // Update the ignore state based on "do()" or "don't()"
                        if ("do()".equals(entry[0])) {
                            ignore.set(false);
                        } else if ("don't()".equals(entry[0])) {
                            ignore.set(true);
                        }

                        return false;
                    }
                    return !ignore.get();
                })
                .mapToInt(entry -> {
                    int first = Integer.parseInt(entry[0]);
                    int second = Integer.parseInt(entry[1]);
                    return first * second;
                })
                .sum();
    }
}
