import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17484_진우의달여행 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0: 왼쪽에서 아래로, 1 : 아래로, 2: 오른쪽 아래로

        int [][][] dp = new int[N][M][3];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                for(int k=0;k<3;k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for(int j=0;j<M;j++) {
            for(int k=0;k<3;k++) {
                dp[0][j][k] = map[0][j];
            }
        }

        for(int i=1;i<N;i++) {
            for(int j=0;j<M;j++) {
                int min = Integer.MAX_VALUE;

                // 왼쪽에서 오는 경우
                if(j > 0) {
                    for(int pre=0;pre<3;pre++) {
                        // 전 움직인 방향 체크
                        if(pre!= 0) {
                            min = Math.min(min, dp[i-1][j-1][pre]);
                        }
                    }
                    dp[i][j][0] = min + map[i][j];
                }


                min = Integer.MAX_VALUE;
                // 오른쪽에서 오는 경우
                if(j<M-1) {
                    for(int pre=0;pre<3;pre++) {
                        // 전 움직인 방향 체크
                        if(pre!=2) {
                            min = Math.min(min,dp[i-1][j+1][pre]);
                        }
                    }
                    dp[i][j][2] = min + map[i][j];
                }


                min = Integer.MAX_VALUE;
                // 가운데에서 오는 경우
                for(int pre=0;pre<3;pre++) {
                    // 전 움직인 방향 체크
                    if(pre != 1) {
                        min = Math.min(min, dp[i-1][j][pre]);
                    }
                }
                dp[i][j][1] = min + map[i][j];
            }
        }

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < 3; j++) {
                min = Math.min(min, dp[N - 1][i][j]);
            }
        }

        System.out.println(min);

    }

}
