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
        HashMap<String, ArrayList<String>> colorKv2 = new HashMap<>();
        for (String in : input) {
            String[] contains = in.split("contain");
            contains[1] = contains[1].replace("no other bags.", "");
            contains[1] = contains[1].replace("bags", "").trim();
            contains[1] = contains[1].replace("bag", "").trim();
            contains[1] = contains[1].replace(".", "").trim();
            String key = contains[0].replace("bags", "").trim();
            colorKv.put(key, contains[1]);
            if (!colorKv2.containsKey(key)) {
                ArrayList<String> val = new ArrayList<>();
                String[] com = contains[1].split(",");
                for (String c : com) {
                    String[] nums = c.trim().split(" ");
                    if (nums[0].trim().matches("\\d+")) {
                        int tmp = Integer.valueOf(nums[0].trim());
                        for (int i = 0; i < tmp; i++) {
                            val.add(nums[1].trim() + " " + nums[2].trim());
                        }
                    }
                }
                colorKv2.put(key, val);
            }
        }

        List<String> sgKey = colorKv.entrySet().stream().filter(val -> val.getValue().contains("shiny gold")).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(recur(sgKey, colorKv));
        Queue<String> find = new ArrayDeque<>();
        find.add("shiny gold");
        int count2 = 0;
        while(!find.isEmpty()){
            String key = find.poll();
            ArrayList<String> values = colorKv2.get(key);
            count2= count2+values.size();
            find.addAll(values);
        }
        System.out.println(count2);
    }

    public static int recur(List<String> inKey, HashMap<String, String> colorKv) {
        Set<String> removedKey = new HashSet<>();
        int count = 0;
        while (!inKey.isEmpty()) {
            Set<String> toAdd = new HashSet<>();
            count = count + inKey.size();
            for (Iterator<String> i = inKey.iterator(); i.hasNext(); ) {
                String key = i.next();
                toAdd.addAll(colorKv.entrySet().stream().filter(val -> val.getValue().contains(key)).map(Map.Entry::getKey).collect(Collectors.toSet()));
            }
            removedKey.addAll(inKey);
            toAdd.removeAll(removedKey);
            inKey.clear();
            inKey.addAll(toAdd);
        }
        return count;
    }

}
