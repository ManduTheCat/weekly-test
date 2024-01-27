package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_14466소가길을건너간이유6 {

    static int N, K, R;
    static Point[] cows;
    static List<Point>[][] bridgeList;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        cows = new Point[K];
        bridgeList = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bridgeList[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;

            bridgeList[r1][c1].add(new Point(r2, c2));
            bridgeList[r2][c2].add(new Point(r1, c1));
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            cows[i] = new Point(r, c);
        }
        System.out.println(start());
    }

    static int start() {

        int cnt = 0;

        // 소 차례대로 각각의 다리를 건너는 경우를 제외하고 갈 수 있는 모든곳을 방문처리
        for(int i = 0; i < K; i++) {
            visited = new boolean[N][N];
            cowMove(cows[i].x, cows[i].y);

            // 각 소마다 방문처리한 결과를 소의 처음위치랑 비교했을때 방문처리 안된곳에 소가 있다면
            // 다리를 건너야 만나는 소이기 때문에 카운트 증가
            for(int j = i; j < K; j++) {
                Point cow = cows[j];
                if(!visited[cow.x][cow.y]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
    static void cowMove(int x, int y) {
        visited[x][y] = true;
//        System.out.println("x : " + x + " y : " + y);

        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
//            System.out.println("nx : " + nx + " ny : " + ny);
            // 배열을 벗어나면 다음 진행
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            // 이미 방문한곳이면 다음 진행
            if(visited[nx][ny]) {
                continue;
            }
            // 다리를 건너야 한다면 다음 진행
            if(bridgeList[x][y].contains(new Point(nx, ny))) {
                continue;
            }
            cowMove(nx, ny);
        }
    }
}
