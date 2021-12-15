import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day12 {

    public static List<String> pathways = new ArrayList<>();

    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("12");
        Pattern start = Pattern.compile("start-([\\w]+)");
        Pattern end = Pattern.compile("([\\w]+)-end");
        Pattern path = Pattern.compile("([\\w]+)-([\\w]+)");
        List<String> small = new ArrayList<>();
        Map<String, List<String>> paths = new HashMap<>();
        for (String line : lines) {
            Matcher startM = start.matcher(line);
            Matcher endM = end.matcher(line);
            Matcher pathM = path.matcher(line);
            if (startM.find()) {
                String cave = startM.group(1);
                if (cave.equals(cave.toLowerCase(Locale.ROOT))) {
                    small.add(cave);
                }
                if (!paths.containsKey("start")) {
                    paths.put("start", new ArrayList<>());
                }
                paths.get("start").add(cave);
            }
            else if (endM.find()) {
                String cave = endM.group(1);
                if (cave.equals(cave.toLowerCase(Locale.ROOT))) {
                    small.add(cave);
                }
                if (!paths.containsKey(cave)) {
                    paths.put(cave, new ArrayList<>());
                }
                paths.get(cave).add("end");
            }
            else {
                pathM.find();
                String cave1 = pathM.group(1);
                String cave2 = pathM.group(2);
                if (cave1.equals(cave1.toLowerCase(Locale.ROOT))) {
                    small.add(cave1);
                }
                if (cave2.equals(cave2.toLowerCase(Locale.ROOT))) {
                    small.add(cave2);
                }
                if (!paths.containsKey(cave1)) {
                    paths.put(cave1, new ArrayList<>());
                }
                if (!paths.containsKey(cave2)) {
                    paths.put(cave2, new ArrayList<>());
                }
                paths.get(cave1).add(cave2);
                paths.get(cave2).add(cave1);
            }
        }

        for (String entry: paths.get("start")) {
            traverse(entry, "start," + entry + ",", paths, small);
        }
        System.out.println(new HashSet<>(pathways).size());
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("12");
        Pattern path = Pattern.compile("([\\w]+)-([\\w]+)");
        Map<String, List<String>> paths = new HashMap<>();
        for (String line : lines) {
            Matcher pathM = path.matcher(line);
            pathM.find();
            String cave1 = pathM.group(1);
            String cave2 = pathM.group(2);
            if (cave1.equals("start") || cave2.equals("start")) {
                if (!paths.containsKey("start")) {
                    paths.put("start", new ArrayList<>());
                }
                paths.get("start").add(cave1.equals("start") ? cave2 : cave1);
            }
            else if (cave1.equals("end") || cave2.equals("end")) {
                String key = cave1.equals("end") ? cave2 : cave1;
                if (!paths.containsKey(key)) {
                    paths.put(key, new ArrayList<>());
                }
                paths.get(key).add("end");
            }
            else {
                if (!paths.containsKey(cave1)) {
                    paths.put(cave1, new ArrayList<>());
                }
                if (!paths.containsKey(cave2)) {
                    paths.put(cave2, new ArrayList<>());
                }
                paths.get(cave1).add(cave2);
                paths.get(cave2).add(cave1);
            }
        }
        pathways = new ArrayList<>();
        for (String entry: paths.get("start")) {
            traverse(entry, "start," + entry + ",", paths);
        }
        Set<String> ret = new HashSet<>(pathways);
        System.out.println(ret.size());
    }

    public static void traverse(String curr, String path, Map<String, List<String>> paths, List<String> small) {
        if (paths.containsKey(curr)) {
            for (String option: paths.get(curr)) {
                if (option.equals("end")) {
                    pathways.add(path + "end");
                }
                else {
                    if (!small.contains(option) || !Arrays.asList(path.split(",")).contains(option)) {
                        traverse(option, path + option + ",", paths, small);
                    }
                }
            }
        }
    }
    public static void traverse(String curr, String path, Map<String, List<String>> paths) {
        if (paths.containsKey(curr)) {
            for (String option: paths.get(curr)) {
                if (option.equals("end")) {
                    pathways.add(path + "end");
                }
                else {
                    if (checkValid(path, option)) {
                        traverse(option, path + option + ",", paths);
                    }
                }
            }
        }
    }

    public static boolean checkValid(String path, String curr) {
        List<String> smalls = Arrays.asList(path.split(",")).stream().filter(i -> i.equals(i.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        if (!smalls.contains(curr) || new HashSet<String>(smalls).size() == smalls.size()) {
            return true;
        }
        return false;
    }
}
