import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("5");
        Pattern reg = Pattern.compile("([\\d]+),([\\d]+) -> ([\\d]+),([\\d]+)");
        Map<Integer, Map<Integer, Integer>> coords = new HashMap<>();
        for(String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            int x1 = Integer.parseInt(m.group(1));
            int y1 = Integer.parseInt(m.group(2));
            int x2 = Integer.parseInt(m.group(3));
            int y2 = Integer.parseInt(m.group(4));
            if(!coords.containsKey(x1)) {
                coords.put(x1, new HashMap<>());
            }

            if (x1 == x2) {
                int smaller = y1 > y2 ? y2 : y1;
                for (int i = 0; i <= (Math.abs(y1 - y2)); i++) {
                    if (!coords.get(x1).containsKey(smaller + i)) {
                        coords.get(x1).put(smaller + i, 0);
                    }
                    coords.get(x1).put(smaller + i, coords.get(x1).get(smaller + i) + 1);
                }
            }
            if (y1 == y2) {
                int smaller = x1 > x2 ? x2 : x1;
                for (int i = 0; i <= (Math.abs(x1 - x2)); i++){
                    if(!coords.containsKey(smaller + i)) {
                        coords.put(smaller + i, new HashMap<>());
                    }
                    if(coords.get(smaller + i).get(y1) == null) {
                        coords.get(smaller + i).put(y1, 0);
                    }
                    coords.get(smaller + i).put(y1, coords.get(smaller + i).get(y1) + 1);
                }
            }
        }
        int total = 0;
        for (int x : coords.keySet()) {
            for (int y : coords.get(x).keySet()) {
                if (coords.get(x).get(y) > 1) {
                    total++;
                }
            }
        }
        System.out.println(total);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("5");
        Pattern reg = Pattern.compile("([\\d]+),([\\d]+) -> ([\\d]+),([\\d]+)");
        Map<Integer, Map<Integer, Integer>> coords = new HashMap<>();
        for(String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            int x1 = Integer.parseInt(m.group(1));
            int y1 = Integer.parseInt(m.group(2));
            int x2 = Integer.parseInt(m.group(3));
            int y2 = Integer.parseInt(m.group(4));
            if(!coords.containsKey(x1)) {
                coords.put(x1, new HashMap<>());
            }

            if (x1 == x2) {
                int smaller = y1 > y2 ? y2 : y1;
                for (int i = 0; i <= (Math.abs(y1 - y2)); i++) {
                    if (!coords.get(x1).containsKey(smaller + i)) {
                        coords.get(x1).put(smaller + i, 0);
                    }
                    coords.get(x1).put(smaller + i, coords.get(x1).get(smaller + i) + 1);
                }
            }
            if (y1 == y2) {
                int smaller = x1 > x2 ? x2 : x1;
                for (int i = 0; i <= (Math.abs(x1 - x2)); i++){
                    if(!coords.containsKey(smaller + i)) {
                        coords.put(smaller + i, new HashMap<>());
                    }
                    if(coords.get(smaller + i).get(y1) == null) {
                        coords.get(smaller + i).put(y1, 0);
                    }
                    coords.get(smaller + i).put(y1, coords.get(smaller + i).get(y1) + 1);
                }
            }
            if (Math.abs(x1 - x2) == Math.abs(y1-y2)) {
                for (int i = 0 ; i <= (Math.abs(x1 - x2)); i++) {
                    int xMove = x1 > x2 ? -i : i;
                    int yMove = y1 > y2 ? -i : i;
                    if(!coords.containsKey(x1 + xMove)) {
                        coords.put(x1 + xMove, new HashMap<>());
                    }
                    if(!coords.get(x1 + xMove).containsKey(y1 + yMove)) {
                        coords.get(x1 + xMove).put(y1 + yMove, 0);
                    }
                    coords.get(x1 + xMove).put(y1 + yMove, coords.get(x1 + xMove).get(y1 + yMove) + 1);
                }
            }
        }
        int total = 0;
        for (int x : coords.keySet()) {
            for (int y : coords.get(x).keySet()) {
                if (coords.get(x).get(y) > 1) {
                    total++;
                }
            }
        }
        System.out.println(total);
    }
}
