import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("13");
        List<Coord> coords = new ArrayList<>();
        List<Fold> folds = new ArrayList<>();
        for (String line: lines) {
            Pattern reg = Pattern.compile("([\\d]+),([\\d]+)");
            Matcher m = reg.matcher(line);
            if (m.find()) {
                int x1 = Integer.parseInt(m.group(1));
                int y1 = Integer.parseInt(m.group(2));
                coords.add(new Coord(x1, y1));
            }
            else {
                Pattern reg1 = Pattern.compile("y=([\\d]+)");
                Pattern reg2 = Pattern.compile("x=([\\d]+)");
                Matcher m1 = reg1.matcher(line);
                Matcher m2 = reg2.matcher(line);
                if (m1.find()) {
                    folds.add(new Fold('y', Integer.parseInt(m1.group(1))));
                }
                else if (m2.find()) {
                    folds.add(new Fold('x', Integer.parseInt(m2.group(1))));
                }
            }
        }
        for (Fold f : folds) {
            List<Coord> remaining = new ArrayList<>();
            for (Coord c : coords) {
                if (f.getLine() == 'x') {
                    if (c.getX() < f.getAmt()) {
                        remaining.add(c);
                    }
                    else if (2 * f.getAmt() >= c.getX()) {
                        Coord nc = new Coord(2 * f.getAmt() - c.getX(), c.getY());
                        remaining.add(nc);
                    }
                }
                else {
                    if (c.getY() < f.getAmt()) {
                        remaining.add(c);
                    }
                    else if (2 * f.getAmt() >= c.getY()) {
                        Coord nc = new Coord(c.getX(), 2 * f.getAmt() - c.getY());
                        remaining.add(nc);
                    }
                }
            }
            coords = remaining;
            break;
        }
        List<Coord> remaining = new ArrayList<>();
        for (Coord c : coords) {
            boolean add = true;
            for (Coord r : remaining) {
                if(c.equals(r)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                remaining.add(c);
            }
        }
        System.out.println(remaining.size());
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("13");
        List<Coord> coords = new ArrayList<>();
        List<Fold> folds = new ArrayList<>();
        for (String line: lines) {
            Pattern reg = Pattern.compile("([\\d]+),([\\d]+)");
            Matcher m = reg.matcher(line);
            if (m.find()) {
                int x1 = Integer.parseInt(m.group(1));
                int y1 = Integer.parseInt(m.group(2));
                coords.add(new Coord(x1, y1));
            }
            else {
                Pattern reg1 = Pattern.compile("y=([\\d]+)");
                Pattern reg2 = Pattern.compile("x=([\\d]+)");
                Matcher m1 = reg1.matcher(line);
                Matcher m2 = reg2.matcher(line);
                if (m1.find()) {
                    folds.add(new Fold('y', Integer.parseInt(m1.group(1))));
                }
                else if (m2.find()) {
                    folds.add(new Fold('x', Integer.parseInt(m2.group(1))));
                }
            }
        }
        for (Fold f : folds) {
            List<Coord> remaining = new ArrayList<>();
            for (Coord c : coords) {
                if (f.getLine() == 'x') {
                    if (c.getX() < f.getAmt()) {
                        remaining.add(c);
                    }
                    else if (2 * f.getAmt() >= c.getX()) {
                        Coord nc = new Coord(2 * f.getAmt() - c.getX(), c.getY());
                        remaining.add(nc);
                    }
                }
                else {
                    if (c.getY() < f.getAmt()) {
                        remaining.add(c);
                    }
                    else if (2 * f.getAmt() >= c.getY()) {
                        Coord nc = new Coord(c.getX(), 2 * f.getAmt() - c.getY());
                        remaining.add(nc);
                    }
                }
            }
            coords = remaining;
        }
        List<Coord> remaining = new ArrayList<>();
        for (Coord c : coords) {
            boolean add = true;
            for (Coord r : remaining) {
                if(c.equals(r)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                remaining.add(c);
            }
        }

        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        for (Coord c : remaining) {
            xs.add(c.getX());
            ys.add(c.getY());
        }
        Collections.sort(xs);
        Collections.sort(ys);
        for (int y = 0; y <= ys.get(ys.size() - 1); y++) {
            for (int x = 0; x <= xs.get(xs.size() - 1); x++) {
                boolean marked = false;
                for (Coord c : remaining) {
                    if (c.getX() == x && c.getY() == y) {
                        System.out.print("X");
                        marked = true;
                        break;
                    }
                }
                if (!marked) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

class Coord {
    public int x;
    public int y;
    public Coord (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean equals(Coord o) {
        if (this.x == o.getX() && this.y == o.getY()) {
            return true;
        }
        return false;
    }
}

class Fold {
    public char line;
    public int amt;
    public Fold (char line, int amt) {
        this.line = line;
        this.amt = amt;
    }

    public char getLine() {
        return line;
    }

    public int getAmt() {
        return amt;
    }
}