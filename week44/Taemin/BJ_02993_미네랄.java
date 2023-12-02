package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_02933_미네랄 {

    static int R, C, N;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R+1][C+1];
        for (int r = 1; r <= R; r++) {
            String line = br.readLine();
            for (int c = 1; c <= C; c++) {
                map[r][c] = line.charAt(c-1);
            }
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            h = calHeight(h);
            throwStick(map, h, i);

            List<int[]> cluster = findCluster(map, h);
            if (cluster != null) {
                eraseCluster(map, cluster);
                int d = findMinDistance(map, cluster);
                moveCluster(map, cluster, d);
            }
        }

        printMap(map);
    }

    public static void eraseCluster(char[][] map, List<int[]> cluster) {
        for (int[] c : cluster) {
            map[c[0]][c[1]] = '.';
        }
    }

    public static void moveCluster(char[][] map, List<int[]> cluster, int d) {
        for (int[] c : cluster) {
            map[c[0]+d][c[1]] = 'x';
        }
    }

    public static int findMinDistance(char[][] map, List<int[]> cluster) {
        int result = Integer.MAX_VALUE;

        for (int[] c : cluster) {
            int row = c[0];
            int col = c[1];

            int distance = 1;
            while (row + distance <= R) {
                if (map[row + distance][col] == 'x') {
                    break;
                }

                distance++;
            }

            distance--;
            result = Math.min(result, distance);
        }

        return result;
    }

    public static int calHeight(int h) {
        return (R + 1) - h;
    }

    public static List<int[]> findCluster(char[][] map, int h) {
        boolean[][] visited = new boolean[R+1][C+1];

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {

                if (!visited[r][c] && map[r][c] == 'x') {
                    Queue<int[]> queue = new LinkedList<>();
                    List<int[]> cluster = new ArrayList<>();

                    queue.add(new int[]{r, c});
                    cluster.add(new int[]{r, c});

                    visited[r][c] = true;
                    int lowest = r;

                    while(!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for (int d = 0; d < 4; d++) {
                            int nextR = cur[0] + dx[d];
                            int nextC = cur[1] + dy[d];

                            if (!validateRange(nextR, nextC)) continue;
                            if (visited[nextR][nextC]) continue;
                            if (map[nextR][nextC] == '.') continue;

                            queue.add(new int[]{nextR, nextC});
                            cluster.add(new int[]{nextR, nextC});
                            visited[nextR][nextC] = true;
                            lowest = Math.max(lowest, nextR);
                        }
                    }

                    if (lowest != R) {
                        return cluster;
                    }
                }
            }
        }

        return null;
    }


    public static boolean validateRange(int r, int c) {
        return (r >= 1 && r <= R && c >= 1 && c <= C);
    }


    public static void throwStick(char[][] map, int h, int i) {
        /* 왼쪽 */
        if (i % 2 == 0) {
            for (int c = 1; c <= C; c++) {
                if (map[h][c] == 'x') {
                    map[h][c] = '.';
                    return;
                }
            }
        }
        /* 오른쪽 */
        else {
            for (int c = C; c >= 1; c--) {
                if (map[h][c] == 'x') {
                    map[h][c] = '.';
                    return;
                }
            }
        }
    }

    public static void printMap(char[][] map) {
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                System.out.print(map[r][c]);
            }
            System.out.println();
        }
    }
}
