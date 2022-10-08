package 모의테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class SW_2105_디저트카페_이우승 {

    static int N;
    static int[][] map;
    static int[] dessert;
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};
    static int ans;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            ans = Integer.MIN_VALUE;
            dessert = new int[101];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N - 2; i++) {
                for(int j = 1; j < N - 1; j++) {
                    dfs(i, j, i, j, 0, 0);
                }
            }
            if(ans == Integer.MIN_VALUE){
                System.out.println("#" + t + " " + -1);
            }else{
                System.out.println("#" + t + " " + ans);
            }

        }

    }

    static void dfs(int sx, int sy, int x, int y, int cnt, int dir){
        if(dir == 4) {
            return;
        }

        if(x == sx && y == sy && dessert[map[x][y]] == 1){
            ans = Math.max(ans, cnt);
            return;
        }

        if(x < 0 || x >= N || y < 0 || y >= N){
            return;
        }

        if(dessert[map[x][y]] == 1){
            return;
        }else {
            dessert[map[x][y]] = 1;
            dfs(sx, sy, x + dx[dir], y + dy[dir], cnt + 1, dir);
            if(dir + 1 < 4){
                dfs(sx, sy, x + dx[dir + 1], y + dy[dir + 1], cnt + 1, dir + 1);
            }
            dessert[map[x][y]] = 0;
        }

    }

}
