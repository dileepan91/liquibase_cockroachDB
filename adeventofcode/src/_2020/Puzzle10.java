package _2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle10 {

    public static void main1(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/_2020/Puzzle10Input.txt"));
        int one = 1;
        int three = 1;
        for (String in : input) {
            int val = Integer.valueOf(in);
            if (input.contains(String.valueOf(val + 1))) {
                one++;
            } else if (input.contains(String.valueOf(val + 3))) {
                three++;
            }
        }
        System.out.println(one + "-" + three);
        System.out.println(one * three);
    }

    public static void main(String[] args) throws IOException {
        List<Integer> input = Files.readAllLines(Paths.get("src/_2020/Puzzle10Input.txt")).stream().map(Integer::parseInt).collect(Collectors.toList());
        Long prob = 1l;
        input.add(0);
        LinkedHashMap<Integer, Long> lastElement = new LinkedHashMap<>();
        Collections.sort(input);
        for (Integer val : input) {
            add(lastElement, val, val, true);
            int c = 0;
            if (input.contains(val + 1)) {
                c++;

                add(lastElement, val+1, val, false);
            }
            if (input.contains(val + 2)) {
                if (c != 0) {

                    Long occ = lastElement.get(val);
                    prob = prob + occ;
                    c++;

                }
                add(lastElement, val+2, val, false);
            }
            if (input.contains(val + 3)) {
                if (c != 0) {
                    Long occ = lastElement.get(val);
                    prob = prob + occ;

                }
                add(lastElement, val + 3, val, false);
            }
            lastElement.remove(val);
        }
        System.out.println(prob);
    }

    public static void add(LinkedHashMap<Integer, Long> lastElement, Integer valP, Integer val, boolean dontOvrd) {
        if (lastElement.containsKey(valP)) {
            if(dontOvrd){
                lastElement.put(valP, lastElement.get(val));
            }else {
                lastElement.put(valP, lastElement.get(valP) + lastElement.get(val));
            }
        } else {
            if(lastElement.containsKey(val)){
                lastElement.put(valP, lastElement.get(val));
            }else {
                lastElement.put(valP, 1l);
            }
        }
    }
}
