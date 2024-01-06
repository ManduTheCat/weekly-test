import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_인싸들의가위바위보 {

    private static int N, K;
    private static int[][] A, RPS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        RPS = new int[3][20];
        for (int i = 1; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 20; j++) {
                RPS[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        System.out.println(perm(0, new boolean[N]) ? "1" : "0");
    }

    private static boolean perm(int cnt, boolean[] visited) {
        if (cnt == N) {
            // 우승할 수 있는지 확인
            if (playRPS(new int[3], 0, 1, new int[3])) return true;
            return false;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            RPS[0][cnt] = i;
            if (perm(cnt + 1, visited)) return true; // 우승할 수 있는 경우를 찾았으면 종료
            visited[i] = false;
        }

        return false;
    }

    private static boolean playRPS(int[] round, int fight1, int fight2, int[] winCnt) {
        // 종료 조건
        if (winCnt[0] == K) return true; // 0 우승
        if (round[0] == N || winCnt[1] == K || winCnt[2] == K) return false; // 모든 경우의 수를 냈거나 다른 사람이 우승

        int winner = fight2;
        if (A[RPS[fight1][round[fight1]]][RPS[fight2][round[fight2]]] == 2) winner = fight1; // 선플레이어는 가위바위보에서 이겨야 승리할 수 있음

        int f = getLeftFighter(fight1, fight2); // 게임에 참여하지 않은 플레이어 구하기

        ++round[fight1];
        ++round[fight2];
        ++winCnt[winner];

        // 무조건 선플레이어가 fight1
        if (f > winner) {
            if (playRPS(round, winner, f, winCnt)) return true;
        } else {
            if (playRPS(round, f, winner, winCnt)) return true;
        }

        --round[fight1];
        --round[fight2];
        --winCnt[winner];

        return false;
    }

    private static int getLeftFighter(int fight1, int fight2) {
        boolean[] chk = new boolean[3];
        chk[fight1] = true;
        chk[fight2] = true;

        for (int i = 0; i < 3; i++) {
            if (!chk[i]) return i;
        }
        return -1;
    }

}
