import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Day2 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines(2);
        int vert = 0;
        int horiz = 0;
        for (String line : lines) {
            String movement = line.split(" ")[0].toLowerCase(Locale.ROOT);
            int amt = Integer.parseInt(line.split(" ")[1]);
            switch (movement) {
                case "forward": horiz += amt;
                    break;
                case "up": vert -= amt;
                    break;
                case "down": vert += amt;
                    break;
            }
        }
        System.out.println(vert * horiz);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines(2);
        int vert = 0;
        int horiz = 0;
        int aim = 0;
        for (String line : lines) {
            String movement = line.split(" ")[0].toLowerCase(Locale.ROOT);
            int amt = Integer.parseInt(line.split(" ")[1]);
            switch (movement) {
                case "forward": horiz += amt;
                                aim += (vert * amt);
                    break;
                case "up": vert -= amt;
                    break;
                case "down": vert += amt;
                    break;
            }
        }
        System.out.println(horiz * aim);
    }
}
