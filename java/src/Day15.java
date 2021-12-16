import java.util.*;
import java.util.stream.Collectors;

public class Day15 {

    public static int[] grid;
    public static int[] dist;
    public static Set<Integer> visited;
    public static PriorityQueue<Node> queue = new PriorityQueue<>();
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("15");
        List<List<Integer>> gridData = new ArrayList<>();
        for (String line : lines) {
            gridData.add(Arrays.asList(line.split("")).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        int dimensions = lines.size();
        grid = new int[dimensions * dimensions];
        for (int i = 0; i < gridData.size(); i++) {
            for (int j = 0; j < gridData.get(i).size(); j++) {
                grid[i * dimensions + j] = gridData.get(i).get(j);
            }
        }
        search(dimensions);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("15");
        List<List<Integer>> gridData = new ArrayList<>();
        for (String line : lines) {
            gridData.add(Arrays.asList(line.split("")).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        int dimensions = lines.size() * 5;
        int len = dimensions / 5;

        grid = new int[dimensions * dimensions];
        for (int i = 0; i < gridData.size(); i++) {
            for (int j = 0; j < gridData.get(i).size(); j++) {
                for(int x = 0; x < 5; x++) {
                    int dup1 = (i * dimensions) + (x * len) + j;
                    int val = gridData.get(i).get(j) + x;
                    val = (val > 9 ? val % 9 : val);
                    grid[dup1] = val;
                }
            }
        }
        for(int i = 1; i < 5; i++) {
            for (int x = 0; x < dimensions * len; x++) {
                int dup = i * dimensions * len + x;
                int val = grid[x] + i;
                val = (val > 9 ? val % 9 : val);
                grid[dup] = val;
            }
        }
        search(dimensions);

    }

    public static void search(int length)  {
        int verts = length * length;

        queue = new PriorityQueue<>();
        visited = new HashSet<>();
        dist = new int[verts];

        for (int i = 0; i < verts; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        queue.add(new Node(0, 0));
        dist[0] = 0;

        while (visited.size() < verts) {
            int curr = queue.remove().node;
            if (!visited.contains(curr)) {
                visited.add(curr);
                viewAdj(curr, verts, length);
            }
        }
        System.out.println(dist[dist.length - 1]);
    }

    public static void viewAdj(int node, int vert, int line) {
        List<Integer> adj = new ArrayList<>();
        if (node - line >= 0) {
            adj.add(node - line);
        }
        if ((node % line) - 1 >= 0) {
            adj.add(node - 1);
        }
        if (node + line < vert) {
            adj.add(node + line);
        }
        if ((node % line) + 1 < line ) {
            adj.add(node + 1);
        }

        for (int n : adj) {
            if (!visited.contains(n)) {
                int d = dist[node] + grid[n];

                if (d < dist[n]) {
                    dist[n] = d;
                }
                queue.add(new Node(n, dist[n]));
            }
        }
    }

    static class Node implements Comparable<Node> {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            if (this.weight < o.weight) {
                return -1;
            }
            if (this.weight > o.weight) {
                return 1;
            }
            return 0;
        }
    }

//    public static int search(List<List<Integer>> grid) {
//        int[][] cost = new int[grid.size()][grid.get(0).size()];
//
//        cost[0][0] = 0;
//
//        for (int i = 1; i < grid.size(); i++ ) {
//            cost[i][0] = cost[i - 1][0] + grid.get(i).get(0);
//        }
//        for (int i = 1; i < grid.get(0).size(); i++ ) {
//            cost[0][i] = cost[0][i-1] + grid.get(0).get(i);
//        }
//
//        for (int y = 1; y < grid.size(); y++) {
//            for (int x = 1; x < grid.get(y).size(); x++) {
//                cost[y][x] = min(cost[y - 1][x], cost[y][x-1]) + grid.get(y).get(x);
//            }
//        }
//        return cost[grid.size() -1][grid.get(0).size() - 1];
//    }
//    public static int min(int a, int b) {
//        return a > b ? b : a;
//    }

//    public static int minDistance(int path[], boolean visited[], int length) {
//        int min = Integer.MAX_VALUE, index = -1;
//        for (int v = 0; v < length; v++)
//            if (visited[v] == false && path[v] <= min) {
//                min = path[v];
//                index = v;
//            }
//
//        return index;
//    }
}



