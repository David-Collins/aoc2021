import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("4");
        List<String> numbers = Arrays.asList(lines.get(0).split(","));
        lines.remove(0);
        lines.remove(0);
        List<List<List<String>>> boards = new ArrayList<>();
        int curr = 0;
        boards.add(new ArrayList<>());
        for(String line : lines) {
            if (line.equals("")) {
                curr++;
                boards.add(new ArrayList<>());
            }
            else {
                List<String> row = Arrays.asList(line.split("\\s+")).stream().filter(val -> !val.equals("")).collect(Collectors.toList());
                boards.get(curr).add(row);
            }
        }
        List<List<String>> winningBoard = new ArrayList<>();
        List<String> calledNums = new ArrayList<>();
        for(int i = 0; i < numbers.size(); i++) {
            calledNums = numbers.subList(0, i + 1);
            for (List<List<String>> board : boards) {
                for(int x = 0; x < 5; x++) {
                    if (calledNums.containsAll(board.get(x))) {
                        winningBoard = board;
                        break;
                    }
                    List<String> yAxis = new ArrayList<>();
                    for (int y = 0; y < 5; y++) {
                        yAxis.add(board.get(y).get(x));
                    }
                    if (calledNums.containsAll(yAxis)) {
                        winningBoard = board;
                        break;
                    }
                }
                if (winningBoard.size() > 0) {
                    break;
                }
            }
            if (winningBoard.size() > 0) {
                break;
            }
        }
        final List<String> calledNumbers = calledNums;
        int total = winningBoard.stream().flatMap(List::stream).filter(num -> !calledNumbers.contains(num)).map(Integer::parseInt).mapToInt(Integer::intValue).sum();
        total = total * Integer.parseInt(calledNumbers.get(calledNumbers.size() - 1));
        System.out.println(total);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("4");
        List<String> numbers = Arrays.asList(lines.get(0).split(","));
        lines.remove(0);
        lines.remove(0);
        List<List<List<String>>> boards = new ArrayList<>();
        int curr = 0;
        boards.add(new ArrayList<>());
        for(String line : lines) {
            if (line.equals("")) {
                curr++;
                boards.add(new ArrayList<>());
            }
            else {
                List<String> row = Arrays.asList(line.split("\\s+")).stream().filter(val -> !val.equals("")).collect(Collectors.toList());
                boards.get(curr).add(row);
            }
        }
        List<List<String>> winningBoard = new ArrayList<>();
        List<String> calledNums = new ArrayList<>();
        String winningNumber = "0";
        boolean finalWin = false;
        for(int i = 0; i < numbers.size(); i++) {
            List<Integer> winningPos = new ArrayList<>();
            calledNums = numbers.subList(0, i + 1);
            for (int f = 0; f < boards.size(); f++) {
                List<List<String>> board = boards.get(f);
                for(int x = 0; x < 5; x++) {
                    List<String> yAxis = new ArrayList<>();
                    for (int y = 0; y < 5; y++) {
                        yAxis.add(board.get(y).get(x));
                    }
                    if (calledNums.containsAll(board.get(x)) || calledNums.containsAll(yAxis)) {
                        winningNumber = numbers.get(i);
                        winningPos.add(f);
                        break;
                    }
                }
            }
            if (winningPos.size() > 0) {
                int pos = 0;
                while (pos < winningPos.size()) {
                    if (boards.size() == 1 && pos + 1 >= winningPos.size()) {
                        winningBoard = boards.get(0);
                        finalWin = true;
                        break;
                    }
                    boards.remove(winningPos.get(pos) - pos);
                    pos++;
                }
                if (finalWin){
                    break;
                }
            }
        }
        final List<String> calledNumbers = calledNums.subList(0, calledNums.indexOf(winningNumber) + 1);
        int total = winningBoard.stream().flatMap(List::stream).filter(num -> !calledNumbers.contains(num)).map(Integer::parseInt).mapToInt(Integer::intValue).sum();
        total = total * Integer.parseInt(winningNumber);
        System.out.println(total);
    }
}
