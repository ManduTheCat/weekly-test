import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16920_확장게임 {

    private static int N, M, P, S[], answer[];
    private static char[][] map;
    private static Queue<int[]>[] Q;
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        S = new int[P];
        Q = new Queue[P];
        answer = new int[P];
        map = new char[N][];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < P; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            Q[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] >= '1' && map[i][j] <= '9') {
                    ++answer[map[i][j] - '1']; // 성의 개수 저장해두기
                }
            }
        }

        // 게임 플레이
        bfs();

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < P; i++) sb.append(answer[i]).append(" ");
        System.out.println(sb);
    }

    private static void bfs() {
        while (true) {
            boolean[] isChange = new boolean[P];
            fillQueue();

            for (int i = 0; i < P; i++) {
                boolean[][] visited = new boolean[N][M];

                while (!Q[i].isEmpty()) {
                    int[] cur = Q[i].poll();

                    if (cur[2] == S[i]) continue; // 이동 횟수가 S[i]를 넘어가면 안 된다.
                    map[cur[0]][cur[1]] = '#'; // 한번 탐색한 곳은 다시 탐색하지 않도록 처리 (안하면 시간초과 난다!!)

                    for (int[] d : dir) {
                        int nr = cur[0] + d[0];
                        int nc = cur[1] + d[1];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M
                                || map[nr][nc] != '.' || visited[nr][nc]) continue;
                        
                        map[nr][nc] = (char) ('1' + i);
                        ++answer[i];

                        isChange[i] = true; // 변경 여부 기록
                        visited[nr][nc] = true;
                        Q[i].add(new int[]{nr, nc, cur[2] + 1});
                    }
                }
            }

            // 종료 조건: 모든 플레이어가 더 이상 확장을 할 수 없을 때 게임이 끝난다.
            boolean isContinue = false;
            for (int i = 0; i < P; i++) {
                if (isChange[i]) isContinue = true;
            }
            if (!isContinue) break;
        }
    }

    // 성의 위치 큐에 넣어두기
    private static void fillQueue() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] >= '1' && map[i][j] <= '9') {
                    Q[map[i][j] - '1'].add(new int[]{i, j, 0});
                }
            }
        }
    }

}
