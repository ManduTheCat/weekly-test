package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16920_확장게임 {

    private static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int[] s = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        char[][] map = new char[n][m];
        for (int r = 0; r < n; r++) {
            String line = br.readLine();
            for (int c = 0; c < m; c++) {
                char type = line.charAt(c);
                if (Character.isDigit(type)) {
                    pq.add(new Node(type - '0', r, c, 0));
                }

                map[r][c] = type;
            }
        }

        while (true) {
            if (!expand(map, s, n, m)) break;
        }

//        printMap(map);
        printResult(map, p);
    }

    private static boolean expand(char[][] map, int[] s, int n, int m) {
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        boolean result = true;
        PriorityQueue<Node> temp = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int d = 0; d < 4; d++) {
                int nextR = cur.r + dx[d];
                int nextC = cur.c + dy[d];

                if (!validateRange(n, m, nextR, nextC)) continue;
                if (map[nextR][nextC] != '.') continue;

                map[nextR][nextC] = Character.forDigit(cur.id, 10);
                if (cur.d + 1 >= s[cur.id - 1]) {
                    temp.add(new Node(cur.id, nextR, nextC, 0));
                } else {
                    pq.add(new Node(cur.id, nextR, nextC, cur.d + 1));
                }
            }
        }

        pq = temp;
        if (pq.isEmpty()) result = false;
        return result;
    }

    private static void printResult(char[][] map, int p) {
        int[] count = new int[p];
        for (char[] line : map) {
            for (char c : line) {
                if (Character.isDigit(c)) {
                    count[c - '1']++;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int c : count) {
            builder.append(c).append(" ");
        }
        System.out.print(builder.toString());
    }

    private static void printMap(char[][] map) {
        for (char[] line : map) {
            for (char c : line) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static boolean validateRange(int n, int m, int r, int c) {
        return (0 <= r && r < n && 0 <= c && c < m);
    }

    private static class Node implements Comparable<Node> {
        int id;
        int r;
        int c;
        int d;

        public Node(int id, int r, int c, int d) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Node other) {
            if (this.id == other.id) {
                return Integer.compare(this.d, other.d);
            }

            return Integer.compare(this.id, other.id);
        }
    }
}
