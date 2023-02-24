import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14940_쉬운최단거리 {

    static int n, m;
    static int[][] map;
    static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        // input
        int[] start = new int[2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        int[][] ans = bfs(start);

        // output
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static int[][] bfs(int[] start) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) ans[i][j] = 0;
                else ans[i][j] = -1;
            }
        }

        q.add(start);
        ans[start[0]][start[1]] = 0;

        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            ++cnt;

            while (size-- > 0) {
                int[] cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = cur[0] + dir[i][0];
                    int nc = cur[1] + dir[i][1];

                    // 범위를 벗어나거나 이미 방문한 곳이라면 continue
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || ans[nr][nc] >= 0)
                        continue;
                    if (map[nr][nc] == 0) { // 방문할 수 없는 곳이라면 방문할 수 없음을 체크하고 continue
                        ans[nr][nc] = 0;
                        continue;
                    }

                    ans[nr][nc] = cnt;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return ans;
    }
}
