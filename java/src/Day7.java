import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> strings = Arrays.asList(Main.readInputLines(7).get(0).split(","));
        List<Integer> numbers = strings.stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> triedNums = new ArrayList<>();
        int lowestFuel = Integer.MAX_VALUE;
        for(int num : numbers) {
            if (!triedNums.contains(num)) {
                int cost = numbers.stream().mapToInt(i -> Math.abs(i - num)).sum();
                if (cost < lowestFuel) {
                    lowestFuel = cost;
                }
                triedNums.add(num);
            }
        }
        System.out.println(lowestFuel);
    }
    public static void part2() {
        List<String> strings = Arrays.asList(Main.readInputLines(7).get(0).split(","));
        List<Integer> numbers = strings.stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> triedNums = new ArrayList<>();
        int lowestFuel = Integer.MAX_VALUE;
        for(int num : numbers) {
            if (!triedNums.contains(num)) {
                int cost = numbers.stream().mapToInt(i -> (Math.abs(i - num) * (Math.abs(i - num) + 1)) / 2).sum();
                if (cost < lowestFuel) {
                    lowestFuel = cost;
                }
                triedNums.add(num);
            }
        }
        System.out.println(lowestFuel);
    }
}
