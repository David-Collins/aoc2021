import java.util.*;
import java.util.stream.Collectors;

public class Day11 {

    public static int flashes = 0;
    public static List<List<Integer>> grid = new ArrayList<>();
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("11");

        for (String line : lines) {
            grid.add(Arrays.asList(line.split("")).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }

        for (int i = 0; i < 100; i++) {
            for (int f = 0; f < grid.size(); f++) {
                List<Integer> row = grid.get(f);
                grid.set(f, row.stream().map(x -> x+1).collect(Collectors.toList()));
            }
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (grid.get(y).get(x) > 9) {
                        flash(x, y);
                    }
                }
            }
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (grid.get(y).get(x) == -1) {
                        flashes++;
                        grid.get(y).set(x, 0);
                    }
                }
            }
        }
        System.out.println(flashes);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("11");
        grid = new ArrayList<>();
        flashes = 0;

        for (String line : lines) {
            grid.add(Arrays.asList(line.split("")).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }

        int count = 0;
        while (!checkValid()) {
            count++;
            for (int f = 0; f < grid.size(); f++) {
                List<Integer> row = grid.get(f);
                grid.set(f, row.stream().map(x -> x+1).collect(Collectors.toList()));
            }
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (grid.get(y).get(x) > 9) {
                        flash(x, y);
                    }
                }
            }
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (grid.get(y).get(x) == -1) {
                        flashes++;
                        grid.get(y).set(x, 0);
                    }
                }
            }
        }
        System.out.println(count);

    }

    public static void flash(int x, int y) {
        if (x > -1 && x < 10 && y > -1 && y < 10) {
            if(grid.get(y).get(x) != -1) {
                grid.get(y).set(x, grid.get(y).get(x) + 1);
            }
            if (grid.get(y).get(x) > 9) {
                grid.get(y).set(x, -1);
                flash(x + 1, y);
                flash(x - 1, y);
                flash(x, y + 1);
                flash(x, y - 1);
                flash(x + 1, y + 1);
                flash(x + 1, y - 1);
                flash(x - 1, y + 1);
                flash(x - 1, y - 1);
            }
        }
    }

    public static boolean checkValid() {
        for (List<Integer> line : grid) {
            Set<Integer> t = new HashSet<>(line);
            if(!t.contains(0) || t.size() > 1) {
                return false;
            }
        }
        return true;
    }
}
