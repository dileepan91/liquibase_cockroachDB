package _2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Puzzle9 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/_2020/Puzzle9Input.txt"));
        List<Long> lt = new LinkedList<>();
        int i = 0;
        for (; i < 25; i++) {
            lt.add(Long.valueOf(input.get(i)));
        }


        Long culprit = 0l;
        for (; i < input.size(); i++) {
            boolean matchFound = false;
            Long val = Long.valueOf(input.get(i));
            for (Long l : lt) {
                if (lt.contains(Math.abs(l - val))) {
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                culprit = val;
                System.out.println(i);
                break;
            } else {
                lt.remove(0);
                lt.add(val);
            }
        }
        System.out.println(culprit);
        int start = 0;
        int end = 0;
        for (int j = 553; j >= 1; j--) {
            Long val = Long.valueOf(input.get(j));
             start = j;
             end = j;
            boolean found = false;
            if (val < culprit) {
                Long s = val;
                for (int k = 1; k <= j; k++) {
                    Long val2 = Long.valueOf(input.get(j-k));
                    s = s + val2;
                    if(s > culprit){
                        break;
                    } else if (s.equals(culprit)){
                        found = true;
                        end = j-k;
                        break;
                    }

                }
                if(found){
                    System.out.println("FOUND");
                    System.out.println(start +" - "+end);
                    break;
                }
            }
        }

        long min = 0;
        long max = 0;
        for(int r=end;r<=start+1;r++){
            Long val = Long.valueOf(input.get(r));
            if(r==end){
                min = val;
            }
            if(val < min){
                min = val;
            }
            if(val > max){
                max = val;
            }
        }
        System.out.println(min+max);
    }
}
