package org.example.day_01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class One {
    private final PriorityQueue<Long> firstPq;
    private final PriorityQueue<Long> secondPq;

    public One(String path) {
        firstPq = new PriorityQueue<>();
        secondPq = new PriorityQueue<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] split = line.trim().split("\\s+");

                firstPq.offer(Long.parseLong(split[0]));
                secondPq.offer(Long.parseLong(split[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long sumOfDifference() {
        long sum = 0;

        while (!firstPq.isEmpty() && !secondPq.isEmpty()) {
            sum += Math.abs(firstPq.peek() - secondPq.peek());

            firstPq.poll();
            secondPq.poll();
        }

        return sum;
    }
}
