package saturday.sat230311;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/*
한 변의 길이가 N인 정사각형 판 ~ 어느 신체 부위도 범위를 벗어나지 않음
(1, 1) ~ (N, N)
쿠키의 신체 : 머리, 심장, 허리, 좌우 팔과 다리
심장 : 빨간 곳
머리 : 삼장 바로 윗 칸에 1칸 크기
좌우 팔 : 심장 양 옆으로 일직선
허리 : 심장 아래 일직선
왼쪽 다리 : 허리 왼쪽 아래, 아래방향 일직선
오른쪽  다리 : 허리 오른쪽 아래, 아래방향 일직선
허리, 팔, 다리의 길이는 1 이상, 너비는 무조건 1
 */
public class BJ_20125_쿠키의신체측정 {
    static int N;
    public static void main(String[] args) throws IOException {
        // 3 3 (심장이 위치한 행과 열의 번호)
        // 1 1 1 1 1 (왼쪽 팔, 오른쪽 팔, 허리, 왼쪽 다리, 오른쪽 다리 길이)
//        String src = "5\n" +
//                "_____\n" +
//                "__*__\n" +
//                "_***_\n" +
//                "__*__\n" +
//                "_*_*_";
//        BufferedReader br = new BufferedReader(new StringReader(src));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine()); // 한 변의 길이 N

        char[][] map = new char[N+1][N+1];
        Point heart = null;
        for(int i = 1; i <= N; i++){
            String s= br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j+1] = s.charAt(j);

                if(heart == null && map[i][j+1] == '*') heart = new Point(i+1, j+1);
            }
        }
        sb.append(heart.x + " " + heart.y + "\n");

        int[] arms = getArm(map, heart);
        int waist = getWaist(map, heart);
        sb.append(heart.y - arms[0] + " ");
        sb.append(arms[1] - heart.y + " ");
        sb.append(waist + " ");
        Point waist_end = new Point(heart.x+waist, heart.y);
        sb.append(getLeg(map, waist_end, -1) + " ");
        sb.append(getLeg(map, waist_end, 1) + " ");

        System.out.println(sb);
    }

    public static int[] getArm(char[][] map, Point heart) {
        int x = heart.x;
        int start = 1;
        while(map[x][start] == '_'){
            start++;
        }

        int end = start;
        while(map[x][end] == '*'){
            end++;
            if(end > N) break;
        }

        return new int[]{start, end-1};
    }

    public static int getWaist(char[][] map, Point heart) {
        int result = 1;

        int x = heart.x;
        int y = heart.y;

        while(map[x+result][y] == '*') {
            result++;
            if(result > N-1-x) break;
        }
        return result-1;
    }

    public static int getLeg(char[][] map, Point waist, int dir) {
        int result = 0;

        int x = waist.x + 1;
        int y = waist.y + dir;

        while(map[x+result][y] == '*') {
            result++;
            if(result > N-x) break;
        }

        return result;
    }

    public static void print(char[][] map){
        int N = map.length-1;
        for(int i = 1; i <= N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(map[i][j+1]);
            }
            System.out.println();
        }
    }
}
