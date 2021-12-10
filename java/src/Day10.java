import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day10 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("10");
        int total = 0;
        for (String line : lines) {
            Stack<String> opens = new Stack<>();
            List<String> errors = new ArrayList<>();
            List<String> chars = Arrays.asList(line.split(""));
            for (int i = 0; i < chars.size(); i++) {
                String c = chars.get(i);
                if (c.equals("<")) {
                    opens.push(">");
                }
                else if (c.equals("(")) {
                    opens.push(")");
                }
                else if (c.equals("[")) {
                    opens.push("]");
                }
                else if (c.equals("{")) {
                    opens.push("}");
                }
                else {
                    String top = opens.pop();
                    if (!top.equals(c)) {
                        errors.add(c);
                    }
                }
            }

            int score = 0;
            for (String c : errors) {
                switch (c) {
                    case ")": score += 3;
                        break;
                    case "]": score += 57;
                        break;
                    case "}": score += 1197;
                        break;
                    case ">": score += 25137;
                        break;
                    default: break;
                }
            }
            total += score;
        }
        System.out.println(total);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("10");
        List<BigInteger> totals = new ArrayList<>();
        for (String line : lines) {
            Stack<String> opens = new Stack<>();
            List<String> errors = new ArrayList<>();
            List<String> chars = Arrays.asList(line.split(""));
            for (int i = 0; i < chars.size(); i++) {
                String c = chars.get(i);
                if (c.equals("<")) {
                    opens.push(">");
                }
                else if (c.equals("(")) {
                    opens.push(")");
                }
                else if (c.equals("[")) {
                    opens.push("]");
                }
                else if (c.equals("{")) {
                    opens.push("}");
                }
                else {
                    String top = opens.pop();
                    if (!top.equals(c)) {
                        errors.add(c);
                    }
                }
            }
            if (errors.size() == 0) {
                BigInteger score = BigInteger.ZERO;
                int pos = opens.size();
                for(int i = 1; i <= pos; i++) {
                    String ch = opens.remove(pos - i);
                    score = score.multiply(BigInteger.valueOf(5));
                    switch (ch) {
                        case ")": score = score.add(BigInteger.ONE);
                            break;
                        case "]": score = score.add(BigInteger.TWO);
                            break;
                        case "}": score = score.add(BigInteger.valueOf(3));
                            break;
                        case ">": score = score.add(BigInteger.valueOf(4));
                            break;
                        default: break;
                    }
                }
                totals.add(score);
            }
        }
        Collections.sort(totals);
        System.out.println(totals.get(totals.size() / 2));
    }
}
