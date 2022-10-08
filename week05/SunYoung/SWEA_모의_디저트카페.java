/*
 * 디저트카페
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu
 * */
package 새터데이.sat221008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_모의_디저트카페 {
   
   static int N, Result;
   static int[][] Map;
   static boolean[][] Visited;
   static boolean[] Dessert;
   static int[][] delta = { {1,1}, {1,-1}, {-1, -1}, {-1, 1} }; //대각선 방향

   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      
      int T = Integer.parseInt(br.readLine());
      for(int tc=1; tc<=T; tc++) {
         N = Integer.parseInt(br.readLine()); //지역의 한변의 길이
         
         Map = new int[N][N]; //디저트 종류 저장할 배열
         for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
               Map[i][j] = Integer.parseInt(st.nextToken());
            }
         }
         
         //입력확인
//         for(int i=0; i<N; i++) {
//            for(int j=0; j<N; j++) {
//               System.out.print(Map[i][j] + " ");
//            }
//            System.out.println();
//         }
         
         Result = 0; //디저트 수의 최대
         
         //범위 탐색하면서 dfs
         for(int i=0; i<N-2; i++) { //밑에 두줄에서는 사각형 만들 수 없고
            for(int j=1; j<N-1; j++) { //양 옆으로 한 줄씩도 사각형 만들 수 없으니까
               Dessert = new boolean[101]; //1~100까지
               int X = i; //시작위치
               int Y = j; //시작위치
               
               Visited = new boolean[N][N];
               dfs(i, j, X, Y, 0, 0);               
            }
         }
         
         //처음 위치도 알고 있어야 되고(해결) 사각형도 만들어야 되는데(해결)....
         
         if(Result == 0) {
            System.out.println("#" + tc + " -1");
         } else {
            System.out.println("#" + tc + " " + Result);
         }
         
      }
      
   }

   private static void dfs(int x, int y, int startX, int startY, int cnt, int dir) {
      //방문체크
      Visited[x][y] = true;
      Dessert[Map[x][y]] = true;
      
      for(int d = dir; d<4; d++) {
         int nx = x + delta[d][0];
         int ny = y + delta[d][1];
         
         //시작위치랑 같은지(근데 한곳만 가는거 안되니까 cnt 1보다 큰 수 일때만)
         if(startX == nx && startY == ny && cnt > 1) {
            Result = Math.max(Result, cnt+1); //값 비교해서 더 큰값으로 결과값
            return;
         }
         
         //범위 안에 있고 방문한 적이 없는지
         if(check(nx, ny) && !Visited[nx][ny]) {
            //그리고 세부 조건들(사각형인지, 디저트 숫자에 중복은 없는지)
            if(!Dessert[Map[nx][ny]]) {
               Visited[nx][ny] = true;
               Dessert[Map[nx][ny]] = true;
               dfs(nx, ny, startX, startY, cnt+1, d);
               Visited[nx][ny] = false; //이래야 다른 사각형도 만들어보니까
               Dessert[Map[nx][ny]] = false;
            }
            
         }
      }
      
   }

   //범위내에 있는지 확인
   private static boolean check(int x, int y) {
      if(x>=0 && y>=0 && x<N && y<N) {
         return true;
      }
      return false;
   }

}
