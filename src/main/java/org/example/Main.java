package org.example;

import org.example.day_01.Two;

public class Main {
    public static void main(String[] args) {
        Two two = new Two("src/main/java/org/example/day_01/input.txt");

        System.out.println(two.similarityScore());
    }
}