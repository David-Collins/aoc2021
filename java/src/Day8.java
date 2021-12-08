import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("8");
        int total = 0;
        for (String line : lines) {
            List<String> outputs = Arrays.asList(line.split("\\|")[1].split(" "));
            outputs = outputs.stream().filter(i -> !i.equals("")).collect(Collectors.toList());
            for (String output : outputs) {
                int len = Arrays.asList(output.split("")).size();
                if ((len == 2 || len == 3 || len == 4 || len == 7)) {
                    total++;
                }
            }
        }
        System.out.println(total);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("8");

        int total = 0;
        for (String line : lines) {
            List<String> outputs = Arrays.asList(line.split("\\|")[1].split(" "));
            outputs = outputs.stream().filter(i -> !i.equals("")).collect(Collectors.toList());
            Map<String, String> decoder = new HashMap<>();

            List<String> inputs = Arrays.asList(line.split("\\|")[0].split(" "));
            inputs = inputs.stream().filter(i -> !i.equals("")).collect(Collectors.toList());

            String eight = inputs.stream().filter(i -> i.length() == 7).collect(Collectors.toList()).get(0);
            String seven = inputs.stream().filter(i -> i.length() == 3).collect(Collectors.toList()).get(0);
            String four = inputs.stream().filter(i -> i.length() == 4).collect(Collectors.toList()).get(0);
            String one = inputs.stream().filter(i -> i.length() == 2).collect(Collectors.toList()).get(0);
            decoder.put("8", eight);
            decoder.put("7", seven);
            decoder.put("4", four);
            decoder.put("1", one);

            List<String> sixninezero = inputs.stream().filter(i -> i.length() == 6).collect(Collectors.toList());
            String six = "";
            for (String num : sixninezero) {
                if (!Arrays.asList(num.split("")).containsAll(Arrays.asList(one.split("")))) {
                    six = num;
                    break;
                }
            }
            sixninezero.remove(six);
            decoder.put("6", six);

            List<String> twofivethree = inputs.stream().filter(i -> i.length() == 5).collect(Collectors.toList());
            String five = "";
            for (String num : twofivethree) {
                if (removeCharacters(num, six).length() == 0) {
                    five = num;
                }
            }
            twofivethree.remove(five);
            decoder.put("5", five);

            String nine = "";
            for (String num : sixninezero) {
                if (removeCharacters(six, five).equals(removeCharacters(six, num))) {
                    nine = num;
                    break;
                }
            }
            sixninezero.remove(nine);
            decoder.put("9", nine);
            decoder.put("0", sixninezero.get(0));

            String two = "";
            for (String num : twofivethree) {
                if (removeCharacters(num, nine).length() > 0) {
                    two = num;
                    break;
                }
            }
            decoder.put("2", two);
            twofivethree.remove(two);
            String three = twofivethree.get(0);
            decoder.put("3", three);

            String currOutput = "";
            for (String output : outputs) {
                for (String key : decoder.keySet()) {
                    if (
                            new ArrayList<>(Arrays.asList(decoder.get(key).split(""))).containsAll(new ArrayList<>(Arrays.asList(output.split(""))))
                            && decoder.get(key).length() == output.length()
                    ) {
                        currOutput += key;
                        break;
                    }
                }
            }
            total += Integer.parseInt(currOutput);
        }
        System.out.println(total);
    }

    public static String removeCharacters(String src, String target) {
        ArrayList<String> toRet = new ArrayList<>();
        List<String> srcSplit = Arrays.asList(src.split(""));
        List<String> targetSplit = Arrays.asList(target.split(""));
        for(String s: srcSplit) {
            if(!targetSplit.contains(s)) {
                toRet.add(s);
            }
        }
        Collections.sort(toRet);
        return String.join("", toRet);
    }
}
