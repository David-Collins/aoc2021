import java.util.*;
import java.util.stream.Collectors;

public class Day9 {

    public static List<List<Integer>> area = new ArrayList<>();
    public static List<List<Boolean>> markers = new ArrayList<>();
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("9");
        List<List<Integer>> area = new ArrayList<>();
        for (String line : lines) {
            List<Integer> row = Arrays.asList(line.split("")).stream().map(Integer::parseInt).collect(Collectors.toList());
            area.add(row);
        }
        List<Integer> lowPoints = new ArrayList<>();
        for(int y = 0; y < area.size(); y++) {
            for(int x = 0; x < area.get(y).size(); x++) {
                int curr = area.get(y).get(x);
                if (
                        (x - 1 < 0 || curr < area.get(y).get(x-1)) &&
                        (x + 1 == area.get(y).size() || curr < area.get(y).get(x+1)) &&
                        (y - 1 < 0 || curr < area.get(y-1).get(x)) &&
                        (y + 1 == area.size() || curr < area.get(y+1).get(x))
                ) {
                    lowPoints.add(curr);
                }
            }
        }
        int total = lowPoints.stream().map(i -> i + 1).mapToInt(Integer::intValue).sum();
        System.out.println(total);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("9");
        for (String line : lines) {
            List<Boolean> entries = new ArrayList<>();
            for(int i = 0; i < line.length(); i++) {
                entries.add(false);
            }
            markers.add(entries);
            List<Integer> row = Arrays.asList(line.split("")).stream().map(Integer::parseInt).collect(Collectors.toList());
            area.add(row);
        }
        List<Integer> sizes = new ArrayList<>();
        for (int y = 0; y < area.size(); y++) {
            for (int x = 0; x < area.get(y).size(); x++) {
                sizes.add(floodFill(x, y));
            }
        }
        Collections.sort(sizes, Collections.reverseOrder());
        int total = sizes.get(0) * sizes.get(1) * sizes.get(2);
        System.out.println(sizes);
        System.out.println(total);
    }
    public static int floodFill(int x, int y) {
        if (x < 0 || x > area.get(0).size() - 1 ||
            y < 0 || y > area.size() - 1 ||
            area.get(y).get(x) == 9 ||
            markers.get(y).get(x)
        ) {
            return 0;
        }
        markers.get(y).set(x, true);
        return 1 + floodFill(x + 1, y) + floodFill(x - 1, y) + floodFill(x, y + 1) + floodFill(x, y - 1);
    }
}
