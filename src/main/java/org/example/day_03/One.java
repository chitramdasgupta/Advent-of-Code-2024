package org.example.day_03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class One {
    private final List<int[]> matches;

    public One(String path) {
        matches = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            while ((line = br.readLine()) != null) {
                String regex = "mul\\((\\d+),(\\d+)\\)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    int num1 = Integer.parseInt(matcher.group(1));
                    int num2 = Integer.parseInt(matcher.group(2));

                    matches.add(new int[]{num1, num2});
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int solve() {
        return matches.stream().map(a -> a[0] * a[1]).mapToInt(Integer::intValue).sum();
    }
}
    