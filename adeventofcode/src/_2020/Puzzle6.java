package _2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Puzzle6 {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/_2020/Puzzle6Input.txt"));
        HashSet<Character> inChar = new HashSet<>();
        HashMap<Character, Integer> kv = new HashMap<>();
        int count = 0;
        int numberOfPersons = 0;
        int count2 = 0;
        for (String in : input) {
            if (in.isEmpty()) {
                count = count + inChar.size();
                int finalNumberOfPersons = numberOfPersons;
                int valCount = (int) kv.values().stream().filter(val -> val == finalNumberOfPersons).count();
                count2 = count2 + valCount;
                inChar.clear();
                kv.clear();
                numberOfPersons = 0;
            } else {
                char[] inCh = in.toCharArray();
                numberOfPersons++;
                for (char inC : inCh) {
                    inChar.add(inC);
                    int c = 0;
                    if (kv.containsKey(inC)) {
                        c = kv.get(inC);
                    }
                    kv.put(inC, ++c);

                }
            }
        }
        if (!inChar.isEmpty()) {
            count = count + inChar.size();
            int finalNumberOfPersons = numberOfPersons;
            int valCount = (int) kv.values().stream().filter(val -> val == finalNumberOfPersons).count();
            count2 = count2 + valCount;
        }
        System.out.println(count);
        System.out.println(count2);
    }
}
