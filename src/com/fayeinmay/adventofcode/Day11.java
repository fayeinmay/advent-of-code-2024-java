package com.fayeinmay.adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 {

    public static String getSolution() {
        long result;

        result = puzzleOne();
        //  result = puzzleTwo();

        return Long.toString(result);
    }

    private static long puzzleOne() {
        List<String> starterStones = Arrays.stream(STONES.trim().split(" ")).collect(Collectors.toList());

        return blink(starterStones, 25);
    }

    private static long blink(List<String> starterStones, long times) {
        List<String> stones = starterStones;
        for (long j = 0; j < times; j++) {
            stones = stones.stream().flatMap(stone -> {
                if (stone.equals("0")) {
                    return Stream.of("1");
                }

                if (stone.length() % 2 == 0) {
                    String firstPart = stone.substring(0, stone.length() / 2);
                    String secondPart = stone.substring(stone.length() / 2);
                    return Stream.of(Long.valueOf(firstPart).toString(), Long.valueOf(secondPart).toString());
                }

                return Stream.of(String.valueOf(Long.parseLong(stone) * 2024));
            }).toList();
        }

        return stones.size();
    }

    private static final String STONES = """
            64554 35 906 6 6960985 5755 975820 0
            """;
}
