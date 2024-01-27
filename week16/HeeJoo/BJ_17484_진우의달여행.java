package saturday.sat230304;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N X M 행렬 : 각 원소의 값은 그 공간을 지날 때 소모되는 연료의 양
 * 이동방식 : 좌하단, 하단, 우하단 세 방향 가능
 * 전에 움직인 방향으로 움직일 수 없음 ~ 같은 방향 연속 움직임 불가능
 * 최소 연료로 달에 도착하기
 */
public class BJ_17484_진우의달여행 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 행
        int m = Integer.parseInt(st.nextToken()); // 열

        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n][m][3];
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], (int)1e9);
            }
        }


        for(int j = 0; j < m; j++) {
            Arrays.fill(dp[0][j], map[0][j]);
        }


        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dp[i][j][1] = map[i][j] + Math.min(dp[i-1][j][0], dp[i-1][j][2]);
                if(j == 0) {
                    dp[i][j][2] = map[i][j] + Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]);
                } else if(j == m - 1) {
                    dp[i][j][0] = map[i][j] + Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]);
                } else {
                    dp[i][j][0] = map[i][j] + Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]);
                    dp[i][j][2] = map[i][j] + Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]);
                }

            }
        }

        int ans = (int)1e9;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < 3; j++) {
                ans = Math.min(ans, dp[n - 1][i][j]);
            }
        }

        System.out.println(ans);

    }
}
