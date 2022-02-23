import java.util.*;

public class Day16 {

    public static Map<String, String> mapping = new HashMap<>();
    public static int versionTotal;
    public static int value;
    public static String bin;
    public static void main(String[] args){
        List<String> lines = Main.readInputLines("16");
        mapping = new HashMap<>();
        mapping.put("0", "0000");
        mapping.put("1", "0001");
        mapping.put("2", "0010");
        mapping.put("3", "0011");
        mapping.put("4", "0100");
        mapping.put("5", "0101");
        mapping.put("6", "0110");
        mapping.put("7", "0111");
        mapping.put("8", "1000");
        mapping.put("9", "1001");
        mapping.put("A", "1010");
        mapping.put("B", "1011");
        mapping.put("C", "1100");
        mapping.put("D", "1101");
        mapping.put("E", "1110");
        mapping.put("F", "1111");

        bin = "";
        for (String s : lines.get(0).split("")) {
            bin += mapping.get(s);
        }

        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        versionTotal = 0;
        parsePacket(bin, 0);
        System.out.println(versionTotal);
    }
    public static void part2() {
        value = 0;
    }

    public static void parsePacket(String bin, int start) {
        if (!Arrays.asList(bin.substring(start).split("")).contains("1")) {
            return;
        }
        int version = Integer.parseInt(bin.substring(start, start + 3), 2);
        versionTotal += version;
        start += 3;
        String type = bin.substring(start, start + 3);
        int typeint = Integer.parseInt(type, 2);
        start += 3;
        if (typeint == 4) {
            String currentBit = bin.substring(start, start + 5);
            start += 5;
            while (currentBit.charAt(0) != '0') {
                currentBit = bin.substring(start, start + 5);
                start += 5;
            }
        }
        else {
            char op = bin.charAt(start);
            start += 1;
            if (op == '0') {
                int leng = Integer.parseInt(bin.substring(start, start + 15), 2);
                start += 15;
                String subpackets = bin.substring(start, start + leng);
                start += leng;
                parsePacket(subpackets, 0);
            }
            else {
                int numSubPackets = Integer.parseInt(bin.substring(start, start + 11), 2);
                start += 11;
            }
        }
        parsePacket(bin, start);
    }

    public static void evaluatePackets(String bin, int start) {
        if (!Arrays.asList(bin.substring(start).split("")).contains("1")) {
            return;
        }
        int version = Integer.parseInt(bin.substring(start, start + 3), 2);
        versionTotal += version;
        start += 3;
        String type = bin.substring(start, start + 3);
        int typeint = Integer.parseInt(type, 2);
        start += 3;
        if (typeint == 4) {
            String currentBit = bin.substring(start, start + 5);
            start += 5;
            while (currentBit.charAt(0) != '0') {
                currentBit = bin.substring(start, start + 5);
                start += 5;
            }
        }
        else {
            char op = bin.charAt(start);
            start += 1;
            if (op == '0') {
                int leng = Integer.parseInt(bin.substring(start, start + 15), 2);
                start += 15;
                String subpackets = bin.substring(start, start + leng);
                start += leng;
                parsePacket(subpackets, 0);
            }
            else {
                int numSubPackets = Integer.parseInt(bin.substring(start, start + 11), 2);
                start += 11;
            }
        }
        parsePacket(bin, start);
    }
}
