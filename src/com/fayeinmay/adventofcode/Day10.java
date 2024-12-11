package com.fayeinmay.adventofcode;

import java.util.*;

public class Day10 {

    public static String getSolution() {
        long result;

        // Create grid
        Map<Integer, List<Integer>> map2D = new HashMap<>();

        int row = 0;
        int col = 0;
        for (int i = 0; i < TOPOGRAPHIC_MAP.length(); i++) {
            char currentChar = TOPOGRAPHIC_MAP.charAt(i);

            if (currentChar != '\n') {
                map2D.putIfAbsent(row, new ArrayList<>());
                String character = String.valueOf(currentChar);
                map2D.get(row).add(col, character.equals(".") ? 11 : Integer.parseInt(character));
                col++;

            }

            if (col == 41) {
                row++;
                col = 0;
            }
        }

        result = puzzleOne(map2D);
        result = puzzleTwo(map2D);

        return Long.toString(result);
    }

    private static long puzzleOne(Map<Integer, List<Integer>> map2D) {
        long totalTrails = 0;

        for (int y = 0; y < map2D.size(); y++) {
            for (int x = 0; x < map2D.get(y).size(); x++) {
                if (map2D.get(y).get(x) == 0) {
                    Set<String> reachedEndings = new HashSet<>();
                    int foundTrails = findTrail(x, y, 1, map2D, reachedEndings);
                    totalTrails += foundTrails;
                }
            }
        }

        return totalTrails;
    }

    private static long puzzleTwo(Map<Integer, List<Integer>> map2D) {
        long totalTrails = 0;

        for (int y = 0; y < map2D.size(); y++) {
            for (int x = 0; x < map2D.get(y).size(); x++) {
                if (map2D.get(y).get(x) == 0) {
                    int foundTrails = findTrail(x, y, 1, map2D, null);
                    totalTrails += foundTrails;
                }
            }
        }

        return totalTrails;
    }

    // Helper search function
    private static int findTrail(int startX, int startY, int nextNumber, Map<Integer, List<Integer>> map2D, Set<String> reachedEndings) {
        int totalTrails = 0;

        for (int[] dir : DIRECTIONS) {
            int dx = dir[0];
            int dy = dir[1];

            int x = startX + dx;
            int y = startY + dy;

            if (map2D.containsKey(y) && x >= 0 && x < map2D.get(y).size() && Objects.equals(map2D.get(y).get(x), nextNumber)) {
                String endingPosition = x + "," + y;

                if (nextNumber == 9) {
                    if (reachedEndings == null) {
                        totalTrails++;
                    } else if (!reachedEndings.contains(endingPosition)) {
                        reachedEndings.add(endingPosition);
                        totalTrails++;
                    }
                } else {
                    totalTrails += findTrail(x, y, nextNumber + 1, map2D, reachedEndings);
                }
            }
        }

        return totalTrails; // not found in any direction
    }

    private static final int[][] DIRECTIONS = {
            {1, 0},  // Horizontal (right)
            {-1, 0}, // Horizontal (left)
            {0, 1},  // Vertical (down)
            {0, -1}, // Vertical (up)
    };

    private static final String TOPOGRAPHIC_MAP = """
            56566534304501010567543210985433456765656
            47887015213432123498698561876322019808765
            30996326012569765486787478901011326719454
            21345437867678894345695323010110458328323
            76543078998667801232184010923234569634912
            89012123456758910321073423874325678765801
            21010010969843210078934014965410987106702
            10121089878102102127125675894303201215010
            89439654389289043036001986765214106304321
            76548701274376558945432787652105687455901
            05456910465985467876013498943034796567812
            12367876544100389860323437410589892378743
            09450987033201276541410526323478901489656
            98541056124334985432569615410564876503456
            67632341034321650124378700198323498912987
            50721050125610743765234893267010567023478
            41890121036781812890103484786567832110569
            32103430549898905632112545691432945021654
            21234589658923456543007631210321876930323
            10045678767810167652108920101210967845610
            20176501658701078981017011234101456934791
            65281012349612569896543210965789321129887
            74392309658543478787612323872370110001236
            89654498767018762123205478961465200100145
            21763567346529456032118567450554321012298
            10892100256432376543089012329689876521347
            21090121187601789434308743018778989430456
            34789032098965438325219650898569876543265
            25676543107178721116778541767430987650101
            18789054236019980007865432054121456765567
            09652112345127872126957654123098387891438
            58543905439034565434548723210367294380129
            45678876098749654349659510301250193210034
            34589232105658701278765465432348984523895
            21090145234343210989852379821023479610796
            00203453456950197870141089760010568723687
            10112762367869086521236781051023478154554
            23234891056778076430145692342310879034563
            54965910189880125031036780123498960123676
            69876101098799034123321099834567254012985
            78765212345688765434210126765650123323476
            """;
}
