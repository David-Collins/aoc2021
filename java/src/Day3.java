import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day3 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("3");
        String epsilon = "";
        String gamma = "";
        for(int i = 0; i < lines.get(0).length(); i++) {
            int zeroCount = 0;
            int oneCount = 0;
            for(String line : lines) {
                if(line.charAt(i) == '0') {
                    zeroCount++;
                }
                else if(line.charAt(i) == '1') {
                    oneCount++;
                }
            }
            epsilon += oneCount > zeroCount ? '1' : '0';
            gamma += oneCount < zeroCount ? '1' : '0';
        }
        System.out.println(Integer.parseInt(epsilon, 2) * Integer.parseInt(gamma, 2));
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("3");
        List<String> filteredGamma = lines.stream().collect(Collectors.toList());
        List<String> filteredEpsilon = lines.stream().collect(Collectors.toList());
        for(int i = 0; i < lines.get(0).length(); i++) {
            final int curr = i;
            if (filteredGamma.size() % 2 == 0 && filteredGamma.stream().filter(line -> line.charAt(curr) == '0').collect(Collectors.toList()).size() * 2 == filteredGamma.size()) {
                filteredGamma = filteredGamma.stream().filter(line -> line.charAt(curr) == '1').collect(Collectors.toList());
            } else {
                List<String> remaining = new ArrayList<>();
                for (String line: filteredGamma) {
                    if (line.charAt(i) == mostPopularBitAtPos(filteredGamma, i)) {
                        remaining.add(line);
                    }
                }
                filteredGamma = remaining;
            }
            if (filteredGamma.size() == 1) {
                break;
            }
        }
        for(int i = 0; i < lines.get(0).length(); i++) {
            final int curr = i;
            if (filteredEpsilon.size() % 2 == 0 && filteredEpsilon.stream().filter(line -> line.charAt(curr) == '0').collect(Collectors.toList()).size() * 2 == filteredEpsilon.size()) {
                filteredEpsilon = filteredEpsilon.stream().filter(line -> line.charAt(curr) == '0').collect(Collectors.toList());
            } else {
                List<String> remaining = new ArrayList<>();
                for (String line: filteredEpsilon) {
                    if (line.charAt(i) == leastPopularBitAtPos(filteredEpsilon, i)) {
                        remaining.add(line);
                    }
                }
                filteredEpsilon = remaining;
            }
            if (filteredEpsilon.size() == 1) {
                break;
            }
        }
        System.out.println(Integer.parseInt(filteredEpsilon.get(0), 2) * Integer.parseInt(filteredGamma.get(0), 2));
    }
    public static char mostPopularBitAtPos(List<String> lines, final int pos) {
        List<String> zeroBit = lines.stream().filter(line -> line.charAt(pos) == '0').collect(Collectors.toList());
        List<String> oneBit = lines.stream().filter(line -> line.charAt(pos) == '1').collect(Collectors.toList());
        return zeroBit.size() > oneBit.size() ? '0' : '1';
    }
    public static char leastPopularBitAtPos(List<String> lines, final int pos) {
        List<String> zeroBit = lines.stream().filter(line -> line.charAt(pos) == '0').collect(Collectors.toList());
        List<String> oneBit = lines.stream().filter(line -> line.charAt(pos) == '1').collect(Collectors.toList());
        return zeroBit.size() < oneBit.size() ? '0' : '1';
    }
}
