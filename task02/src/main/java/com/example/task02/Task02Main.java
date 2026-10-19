package com.example.task02;

import java.util.stream.IntStream;

public class Task02Main {

    public static void main(String[] args) {

        /*
        cycleGrayCode(2)
                .limit(10)
                .forEach(System.out::println);
        */

    }

    public static IntStream cycleGrayCode(int n) {
        if (n < 1 || n > 16) {
            throw new IllegalArgumentException("n must be between 1 and 16");
        }
        int size = 1 << n;
        return IntStream.iterate(0, i -> (i + 1) % size)
                .map(i -> i ^ (i >> 1));
    }

}
