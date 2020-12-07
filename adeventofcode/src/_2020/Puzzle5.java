package _2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Puzzle5 {

    public static void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Paths.get("src/_2020/Puzzle5Input.txt"));
        double seatId = 0;
        List<Double> seatIds = new ArrayList<>();
        for (String in : input) {
            char[] inChAr = in.toCharArray();
            double[] rowRange = new double[]{0, 127};
            double[] columnRange = new double[]{0, 7};
            for (char inCh : inChAr) {
                switch (inCh) {
                    case 'B':
                        rowRange[0] = Math.ceil((rowRange[0] + rowRange[1]) / 2);
                        break;
                    case 'F':
                        rowRange[1] = Math.floor((rowRange[1] + rowRange[0]) / 2);
                        break;
                    case 'R':
                        columnRange[0] = Math.ceil((columnRange[0] + columnRange[1]) / 2);
                        break;
                    case 'L':
                        columnRange[1] = Math.floor((columnRange[1] + columnRange[0]) / 2);
                        break;
                }
            }
            double tmpSeatId = (rowRange[0] * 8) + columnRange[0];
            if (seatId < tmpSeatId) {
                seatId = tmpSeatId;
            }
            seatIds.add(tmpSeatId);
        }

        int max = (int) Math.round(Collections.max(seatIds));
        int min = (int) Math.round(Collections.min(seatIds));
        IntStream.rangeClosed(min, max).forEach(i -> {
            if (!seatIds.contains((double) i)) {
                System.out.println(i);
            }
        });

    }
}
