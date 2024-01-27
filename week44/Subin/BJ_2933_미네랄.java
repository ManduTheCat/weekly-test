import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2933_미네랄 {

    private static int R, C;
    private static char[][] map;
    private static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][];
        for (int i = R - 1; i >= 0; i--) {
            map[i] = br.readLine().toCharArray();
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (i % 2 == 0) {
                for (int j = 0; j < C; j++) {
                    if (map[x - 1][j] == 'x') {
                        map[x - 1][j] = '.';
                        break;
                    }
                }
            } else {
                for (int j = C - 1; j >= 0; j--) {
                    if (map[x - 1][j] == 'x') {
                        map[x - 1][j] = '.';
                        break;
                    }
                }
            }
            fall();
        }

        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void fall() {
        // 클러스터 체크
        boolean[][] visited = new boolean[R][C];
        for (int j = 0; j < C; j++) {
            if (map[0][j] == 'x' && !visited[0][j]) {
                bfs(0, j, visited);
            }
        }

        // 공중에 떠 있는 미네랄 체크
        int height = R;
        for (int j = 0; j < C; j++) {
            int floor = 0;
            for (int i = 0; i < R; i++) {
                if (visited[i][j]) floor = i + 1;
                if (map[i][j] == 'x' && !visited[i][j]) {
                    height = Math.min(height, i - floor);
                    break;
                }
            }
        }

        // 클러스터 내려주기
        for (int j = 0; j < C; j++) {
            for (int i = 0; i < R; i++) {
                if (map[i][j] == 'x' && !visited[i][j]) {
                    map[i][j] = '.';
                    map[i - height][j] = 'x';
                }
            }
        }
    }

    private static void bfs(int r, int c, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C
                        || visited[nr][nc] || map[nr][nc] != 'x') continue;

                q.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
    }

}
