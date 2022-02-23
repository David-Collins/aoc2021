import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day22 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("22");
        Pattern reg = Pattern.compile("(on|off) x=(-?\\d+)\\.\\.(-?\\d+),y=(-?\\d+)\\.\\.(-?\\d+),z=(-?\\d+)\\.\\.(-?\\d+)");
        Set<Cube> cubes = new HashSet<>();
        for (String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            String onoff = m.group(1);
            int x1 = Integer.parseInt(m.group(2));
            int x2 = Integer.parseInt(m.group(3));
            int y1 = Integer.parseInt(m.group(4));
            int y2 = Integer.parseInt(m.group(5));
            int z1 = Integer.parseInt(m.group(6));
            int z2 = Integer.parseInt(m.group(7));
            if (x1 > 50 || x2 < -50 ||
                    y1 > 50 || y2 < -50 ||
                    z1 > 50 || z2 < -50) {
                break;
            }
            for (int x = x1; x<= x2; x++) {
                if (x >= -50 && x <= 50) {
                    for (int y = y1; y <= y2; y++) {
                        if (y >= -50 && y <= 50) {
                            for (int z = z1; z <= z2; z++) {
                                if (z >= -50 && z <= 50) {
                                    if (onoff.equals("on")) {
                                        cubes.add(new Cube(x, y, z));
                                    } else {
                                        cubes.remove(new Cube(x, y, z));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(cubes.size());
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("22-test");
        Pattern reg = Pattern.compile("(on|off) x=(-?\\d+)\\.\\.(-?\\d+),y=(-?\\d+)\\.\\.(-?\\d+),z=(-?\\d+)\\.\\.(-?\\d+)");
        for (String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            String onoff = m.group(1);
            int x1 = Integer.parseInt(m.group(2));
            int x2 = Integer.parseInt(m.group(3));
            int y1 = Integer.parseInt(m.group(4));
            int y2 = Integer.parseInt(m.group(5));
            int z1 = Integer.parseInt(m.group(6));
            int z2 = Integer.parseInt(m.group(7));
        }
    }

    static class Cube {
        int x, y, z;
        public Cube(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int hashCode() {
            return x + y + z;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof Cube))
                return false;
            Cube other = (Cube) obj;
            if (other.x != x || other.y != y || other.z != z)
                return false;
            return true;
        }
    }
}
