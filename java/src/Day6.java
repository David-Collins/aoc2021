import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        String line = Main.readInputLines("6").get(0);
        List<Integer> fish = Arrays.stream(line.split(",")).map(f -> Integer.parseInt(f)).collect(Collectors.toList());
        for (int i = 0; i < 80; i++) {
            List<Integer> newFish = new ArrayList<>();
            for(int f : fish) {
                if (f > 0) {
                    newFish.add(f - 1);
                }
                else {
                    newFish.add(6);
                    newFish.add(8);
                }
            }
            fish = newFish;
        }
        System.out.println(fish.size());
    }
    public static void part2() {
        String line = Main.readInputLines("6").get(0);
        List<Integer> fish = Arrays.stream(line.split(",")).map(f -> Integer.parseInt(f)).collect(Collectors.toList());
        HashMap<Integer, BigInteger> countdowns = new HashMap<>();
        for(int f : fish) {
            if (countdowns.containsKey(f)) {
                countdowns.put(f, countdowns.get(f).add(BigInteger.ONE));
            }
            else {
                countdowns.put(f, BigInteger.ONE);
            }
        }
        for (int i = 0; i < 256; i++) {
            HashMap<Integer, BigInteger> temp = new HashMap<>();
            for (int x = 8; x >= 0; x--) {
                if (temp.get(x) == null) {
                    temp.put(x, BigInteger.ZERO);
                }
                if (x == 0) {
                    temp.put(8, (countdowns.get(x) == null ? BigInteger.ZERO : countdowns.get(x)));
                    temp.put(6, temp.get(6).add(countdowns.get(x) == null ? BigInteger.ZERO : countdowns.get(x)));
                }
                else {
                    temp.put(x - 1, countdowns.get(x));
                }
            }
            countdowns = temp;
        }
        System.out.println(countdowns);
        BigInteger total = BigInteger.ZERO;
        for (int age : countdowns.keySet()) {
            BigInteger bigAge = BigInteger.valueOf(countdowns.get(age).intValue());
            total = total.add(bigAge);
        }
        System.out.println(total);
    }
}
