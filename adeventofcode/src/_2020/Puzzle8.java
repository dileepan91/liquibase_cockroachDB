package _2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class Puzzle8 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/_2020/Puzzle8Input.txt"));
        int acc = 0;
        HashSet<Integer> count = new LinkedHashSet<>();
        HashSet<Integer> nops = new LinkedHashSet<>();
        HashSet<Integer> jmps = new LinkedHashSet<>();
        int i = 0;
        for (; i < input.size(); ) {
            if (!count.contains(i)) {
                count.add(i);
                String in[] = input.get(i).split(" ");
                switch (in[0]) {
                    case "acc":
                        acc = acc + (Integer.valueOf(in[1]));
                        i++;
                        break;
                    case "jmp":
                        jmps.add(i);
                        i = i + (Integer.valueOf(in[1]));
                        break;
                    case "nop":
                        nops.add(i);
                        i++;
                        break;

                }
            } else {
                break;
            }
        }
        System.out.println(acc);
        calc(nops,input,"nops");
        calc(jmps,input,"jmps");
    }

    public static void calc( HashSet<Integer> nops, List<String> input, String swap){
        HashSet<Integer> count = new HashSet<>();


        for (Integer a : nops) {
            int i2 = 0;
            int acc2 = 0;
            count.clear();
            boolean perfect = false;
            for (; i2 < input.size(); ) {
                if (!count.contains(i2)) {
                    count.add(i2);
                    String in[] = input.get(i2).split(" ");
                    switch (in[0]) {
                        case "acc":
                            acc2 = acc2 + (Integer.valueOf(in[1]));
                            i2++;
                            break;
                        case "jmp":
                            if(swap.equals("jmps") && i2 == a){
                                i2++;
                            } else {
                                i2 = i2 + (Integer.valueOf(in[1]));
                            }
                            break;
                        case "nop":
                            if (swap.equals("nops") && i2 == a) {
                                i2 = i2 + (Integer.valueOf(in[1]));
                            } else {
                                i2++;
                            }
                            break;
                    }
                } else {
                    perfect = true;
                    break;
                }
            }
            if(!perfect){
                System.out.println("PERFECT!!!!!" + a + "--"+acc2);
                break;
            }
        }

    }
}
