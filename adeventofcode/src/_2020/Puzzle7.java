package _2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Puzzle7 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/_2020/Puzzle7Input.txt"));
        HashMap<String, String> colorKv = new HashMap<>();
        for (String in : input) {
            String[] contains = in.split("contain");
            colorKv.put(contains[0], contains[1]);
        }

        List<String> sgKey = colorKv.entrySet().stream().filter(val -> val.getValue().contains("shiny gold")).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(recur(sgKey, colorKv));
    }

    public static int recur(List<String> inKey, HashMap<String, String> colorKv) {
        Set<String> removedKey = new HashSet<>();
        int count = 0;
        while (!inKey.isEmpty()) {
            Set<String> toAdd = new HashSet<>();
            count = count + inKey.size();
            for (Iterator<String> i = inKey.iterator(); i.hasNext(); ) {
                String key = i.next();
                String keyFinal = key.contains("bags") ? key.replace("bags","") : key.replace("bag","");
                toAdd.addAll(colorKv.entrySet().stream().filter(val -> val.getValue().contains(keyFinal.trim())).map(Map.Entry::getKey).collect(Collectors.toSet()));
            }
            removedKey.addAll(inKey);
            toAdd.removeAll(removedKey);
            inKey.clear();
            inKey.addAll(toAdd);
        }
        return count;
    }

 }
