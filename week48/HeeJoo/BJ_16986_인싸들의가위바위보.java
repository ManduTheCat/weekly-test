package saturday.sat240106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 필요 승수, 경기 진행 순서 미리 합의(A, B, C)
 * 2. 먼저 A와 B 경기 진행 후 승자 정하기 ~ 무승부가 발생한 경우 진행 순서상 뒤인 사람이 승자
 * 3. 이전 경기의 승자와 이전 경기에 참여하지 않은 사람이 경기를 진행해 승자 결정
 * 4. 특정 사람이 미리 합의된 승수를 달성할 때 까지 3단계 반복
 * 5. 합의된 승수를 최초로 달성한 사람이 우승
 * A가 B, C의 손동작 순서를 알 때, 모든 손동작을 다르게 내어 우승할 수 있는지 판단하는 프로그램
 * 진행 순서는 A, B, C
 *
 * 이김(2), 비김(1), 짐(0)
 */
public class BJ_16986_인싸들의가위바위보 {
    static int N, K;
    static int[][] map;
    static boolean ans = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 가위바위보 손동작 수
        K = Integer.parseInt(st.nextToken()); // 필요 승수

        if(N < K) {
            System.out.println(0);
            return;
        }

        map = new int[N + 1][N + 1]; // 상성표
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int idx = 1;
            while(st.hasMoreTokens()) {
                map[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] seq = new int[3 + 1][21];
        for(int i = 2; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 20; j++) {
                seq[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // A의 손동작 순열 구하기
        boolean[] visited = new boolean[N + 1];
        perm(1, seq, visited);

        if(ans) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    public static void perm(int n, int[][] seq, boolean[] visited) {
        if(ans) {
            return;
        }

        if(n > N) {
//            for(int i = 1; i < 4; i++) {
//                for(int j = 1; j <= N; j++) {
//                    System.out.print(seq[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//             가위바위보

            if(game(seq)) {
                ans = true;
            }

            return;
        }

        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                seq[1][i] = n;
                perm(n + 1, seq, visited);
                visited[i] = false;
                seq[1][i] = 0;
            }
        }
    }

    public static boolean game(int[][] seq) {
        int[] winCnt = new int[4];
        int[] handIdx = new int[4];
        Arrays.fill(handIdx, 1);

        int p1 = 1, p2 = 2, next = 0;

        while(true) {
            next = 6 - p1 - p2;

            if(winCnt[1] == K) {
                return true;
            }

            if(winCnt[2] == K || winCnt[3] == K) {
                return false;
            }

            if(handIdx[1] == N + 1 || handIdx[2] == 21 || handIdx[3] == 21) {
                return false;
            }

            int result = map[seq[p1][handIdx[p1]++]][seq[p2][handIdx[p2]++]];
            int winner = -1;
            if(result == 2) { // 1p 승리
                winner = p1;
            } else if (result == 0){ // 2p 승리
                winner = p2;
            } else { // 비김 ~ 뒷 순서 승리
                if(p1 < p2) {
                    winner = p2;
                } else {
                    winner = p1;
                }
            }

            winCnt[winner]++;
            p1 = winner;
            p2 = next;

        }
    }

    public static int[] charToInt(String[] arr) {
        int[] result = new int[arr.length];

        for(int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }

        return result;
    }
}
