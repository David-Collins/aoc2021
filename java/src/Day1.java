import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Day1 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines(1);
        int startingPos = 0;
        int nextPos = 1;
        int count = 0;
        while (nextPos < lines.size()) {
            if (Integer.parseInt(lines.get(nextPos)) > Integer.parseInt(lines.get(startingPos))) {
                count++;
            }
            startingPos++;
            nextPos++;
        }
        System.out.println(count);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines(1);
        List<Integer> windows = new ArrayList<>();
        int pos = 0;
        while (pos + 2 < lines.size()) {
            int num1 = Integer.parseInt(lines.get(pos));
            int num2 = Integer.parseInt(lines.get(pos + 1));
            int num3 = Integer.parseInt(lines.get(pos + 2));
            int total = num1 + num2 + num3;
            windows.add(total);
            pos++;
        }
        int count = 0;
        int prev = 0;
        pos = 1;
        while (pos < windows.size()) {
            if (windows.get(pos) > windows.get(prev)) {
                count ++;
            }
            pos++;
            prev++;
        }
        System.out.println(count);
    }
}
