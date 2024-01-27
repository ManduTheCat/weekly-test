import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1890_점프_이우승 {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        long[][] dp = new long[N][N];

        dp[0][0] = 1;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++){
                int d = Integer.parseInt(st.nextToken());

                int nx = i + d;
                int ny = j + d;

                if(dp[i][j] >= 1 && d != 0) {
                    if (ny < N){
                        dp[i][ny] += dp[i][j];
                    }
                    if (nx < N){
                        dp[nx][j] += dp[i][j];
                    }
                }
            }
        }

        System.out.println(dp[N-1][N-1]);

    }

}
