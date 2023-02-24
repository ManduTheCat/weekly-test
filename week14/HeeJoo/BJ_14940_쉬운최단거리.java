package Sat_230223;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 모든 지점에 대해서 목표지점까지의 거리 구하기
public class BJ_14940_쉬운최단거리 {
    static int n, m;
    static int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        String src = "15 15\n" +
                "2 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 0 0 0 0 1\n" +
                "1 1 1 1 1 1 1 1 1 1 0 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1 0 1 0 0 0\n" +
                "1 1 1 1 1 1 1 1 1 1 0 1 1 1 1";
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new StringReader(src));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로

        // n개의 줄에 m개의 숫자
        // 0은 갈 수 없음. 1은 가짐. 2는 목표
        int[][] map = new int[n][m];
        Point dest = null;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2) dest = new Point(i, j);
            }
        }

        visited = new boolean[n][m];

        map = bfs(map, dest);

        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 1){
                    if(visited[i][j]) sb.append(map[i][j] + " ");
                    else sb.append("-1 ");
                } else sb.append(map[i][j] + " " );
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static int[][] bfs(int[][] map, Point p){
        visited[p.x][p.y] = true;

        Queue<Point> q = new ArrayDeque<>();
        q.offer(p);
        int size = q.size();
        int dist = 0;

        while(!q.isEmpty()){
            for(int i = 0; i < size; i++){
                Point now = q.poll();
                int x = now.x;
                int y = now.y;
                map[x][y] = dist;

                for(int j = 0; j < 4; j++){
                    int dx = x + dir[j][0];
                    int dy = y + dir[j][1];

                    if(dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
                    if(map[dx][dy] != 1) continue;
                    if(visited[dx][dy]) continue;

                    q.offer(new Point(dx, dy));
                    visited[dx][dy] = true;
                    map[dx][dy] = dist;
                }
            }
            size = q.size();
            dist++;
        }

        return map;
    }

    public static void print(int[][] map){
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
