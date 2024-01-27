package saturday.sat230121;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
NxN 격자 목초지 (2 <= N <= 100)
인접한 목초지 사이는 일반적으로 자유롭게 건너기 가능
but 일부는 길을 건너야 함
K마리(1 <= K <= 100, K <= N^2) 소가 농장에 있고, 각 소는 다른 목초지에 있다.
어떤 두 소는 길을 건너지 않으면 만나지 못 할 수 있다. << 이런 소가 몇 쌍인지 세어보는 프로그램
 */
public class BJ_14466_소가길을건넌이유6 {
    static int N, K, R;
    static int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    static int[][] map;
    static Point[] cows;
    static ArrayList<Point>[][] road;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 목초지의 크기 N
        K = Integer.parseInt(st.nextToken()); // 농장에 존재하는 소의 수 K
        R = Integer.parseInt(st.nextToken()); // 입력할 길의 개수 R

        // 길 입력
        // 좌표 (x, y)와 연결된 길의 도착 좌표
        road = new ArrayList[N+1][N+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++) road[i][j] = new ArrayList<>();
        }

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            road[r1][c1].add(new Point(r2, c2));
        }

        // 소들의 좌표 입력
        cows = new Point[K];
        map = new int[N+1][N+1];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            cows[i] = new Point(x, y);
            map[x][y] = 1;
        }

        // 테스트
        print(map);

        // i번째 소를 기준으로 BFS
        int result = 0;
        for(int i = 0; i < K - 1; i++){
            result += BFS(i);
        }

        System.out.println("정답 : " + result);
    }

    /*
    소의 시작 위치를 기준으로 BFS
    길이 있는 경우 탐색X
    다른 소를 만나면 해당 index를 true 체크 ~ 마지막 false 개수 세기
    (1, 2) = (2, 1)이니 탐색을 하는 소 보다 뒷 index 소만 체크해야 함
     */
    public static int BFS(int target){
        int count = 0;

        boolean[][] visited = new boolean[N+1][N+1];
        boolean[] contacted = new boolean[N];

        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(cows[target]);

        visited[cows[target].x][cows[target].y] = true;
        contacted[target] = true;

        while(!queue.isEmpty()){
            Point cow = queue.poll();
            System.out.println(cow.x + ", " + cow.y);
            // 이동해서 소를 만난 경우
            if(map[cow.x][cow.y] == 1){
                // 만난 소가 출발 소보다 뒷 번호일 경우만 contacted check
                for(int i = target + 1; i < K; i++){
                    Point meetCow = cows[i];

                    if(meetCow.x == cow.x && meetCow.y == cow.y){
                        // 테스트
                        System.out.println(target + ", " + i);
                        contacted[i] = true;
                        break;
                    }
                }
            }

            // 사방향 이동
            for(int i = 0; i < 4; i++){
                // 새로 이동할 좌표
                int dx = cow.x + dir[i][0];
                int dy = cow.y + dir[i][1];

                // 범위 체크
                if(dx <= 0 || dx > N || dy <= 0 || dy > N) continue;

                // 방문 체크
                if(visited[dx][dy]) continue;

                // 길 연결 체크
                // 등록할 때 단방향으로 해놔서 여기서 양방향 체크
                if(road[cow.x][cow.y].contains(new Point(dx, dy)) || road[dx][dy].contains(new Point(cow.x, cow.y))) continue;

                // 테스트
                System.out.printf("dx, dy : %d, %d\n", dx, dy);
                visited[dx][dy] = true;
                queue.offer(new Point(dx, dy));
            }
            System.out.println("다음\n");
        }

        // 모든 탐색을 끝난 후 만나지 못한 소의 쌍 구하기
        for(int i = target + 1; i < K; i++){
            if(!contacted[i]) count++;
        }

        return count;
    }

    public static void print(int[][] array){
        for(int[] arr : array){
            for(int a : arr){
                System.out.print(a + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void print(int[] array){
        for(int a : array) System.out.print(a + " ");

        System.out.println();
    }
}
