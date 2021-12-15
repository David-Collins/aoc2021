import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day14 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("14");
        String pattern = lines.get(0);
        lines.remove(0);
        lines.remove(0);
        Map<String, String> input = new HashMap<>();
        for (String line : lines) {
            List<String> split = Arrays.asList(line.split(" -> "));
            input.put(split.get(0), split.get(1));
        }
        for(int i = 0; i < 10; i++) {
            String curr = "";
            for (int x = 0; x < pattern.length() - 1; x++) {
                curr += pattern.charAt(x);
                String check = "" + pattern.charAt(x) + pattern.charAt(x + 1);
                if (input.containsKey(check)) {
                    curr += input.get(check);
                }
            }
            curr += pattern.charAt(pattern.length() - 1);
            pattern = curr;
        }
        int most = -1;
        int least = Integer.MAX_VALUE;
        List<String> chars = Arrays.asList(pattern.split(""));
        for (String ch : new HashSet<>(chars)) {
            int count = chars.stream().filter(i -> i.equals(ch)).collect(Collectors.toList()).size();
            if (count < least) {
                least = count;
            }
            if (count > most) {
                most = count;
            }
        }
        System.out.println(most - least);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("14");
        String pattern = lines.get(0);
        lines.remove(0);
        lines.remove(0);
        Map<String, String> charMap = new HashMap<>();
        for (String line : lines) {
            List<String> split = Arrays.asList(line.split(" -> "));
            charMap.put(split.get(0), split.get(1));
        }
        Map<String, BigInteger> pairCount = new HashMap<>();
        for (int x = 0; x < pattern.length() - 1; x++) {
            String check = "" + pattern.charAt(x) + pattern.charAt(x + 1);
            if (!pairCount.containsKey(check)) {
                pairCount.put(check, BigInteger.ONE);
            }
            else {
                pairCount.put(check, pairCount.get(check).add(BigInteger.ONE));
            }
        }
        HashMap<String, BigInteger> letterCount = new HashMap<>();
        for (int x = 0; x < pattern.length(); x++) {
            String check = "" + pattern.charAt(x);
            if (!letterCount.containsKey(check)) {
                letterCount.put(check, BigInteger.ONE);
            }
            else {
                letterCount.put(check, letterCount.get(check).add(BigInteger.ONE));
            }
        }

        for(int i = 0; i < 40; i++) {
            Set<String> keys = new HashSet<>(pairCount.keySet());
            List<Map<String, BigInteger>> updates = new ArrayList<>();
            for (String key : keys) {
                if (!pairCount.get(key).equals(BigInteger.ZERO)) {
                    Map<String, BigInteger> update = new HashMap<>();
                    if (charMap.containsKey(key)) {
                        String mapped = charMap.get(key);
                        BigInteger pairC = pairCount.get(key);
                        String newPair1 = key.charAt(0) + mapped;
                        String newPair2 = mapped + key.charAt(1);
                        if (letterCount.containsKey(mapped)) {
                            letterCount.put(mapped, letterCount.get(mapped).add(pairC));
                        }
                        else {
                            letterCount.put(mapped, pairC);
                        }
                        update.put(key, pairC.multiply(BigInteger.valueOf(-1)));
                        if (update.containsKey(newPair1)) {
                            update.put(newPair1, BigInteger.ZERO);
                        }
                        else {
                            update.put(newPair1, pairC);
                        }
                        if (update.containsKey(newPair2)) {
                            update.put(newPair2, BigInteger.ZERO);
                        }
                        else {
                            update.put(newPair2, pairC);
                        }
                    }
                    updates.add(update);
                }
            }
            for(Map<String, BigInteger> changes: updates) {
                for (String pair: changes.keySet()) {
                    if (pairCount.containsKey(pair)) {
                        pairCount.put(pair, pairCount.get(pair).add(changes.get(pair)));
                    }
                    else {
                        pairCount.put(pair, changes.get(pair));
                    }
                }
            }
        }
        List<BigInteger> vals = letterCount.values().stream().collect(Collectors.toList());
        Collections.sort(vals);
        System.out.println(vals.get(vals.size() - 1).subtract(vals.get(0)));
    }
}
