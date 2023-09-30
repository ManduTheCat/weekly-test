/*
 * 이차원 배열 맵에 테두리만 그리기
 */

import java.util.*;

class Solution {
    static int answer = (int)1e9;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌 -> 하우상좌
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        int x = 0, y = 0;
        for(int[] arr : rectangle) {
            x = Math.max(x, arr[2]);
            y = Math.max(y, arr[3]);
        }
        
        int[][] map = new int[x * 2 + 2][y * 2 + 2];
        // 1. 테두리 그리기
        for(int t = 0; t < rectangle.length; t++) {
            // 좌표계 2배로 늘리기 ~ why ? 한 칸 간격 오류 발생 방지
            int x1 = rectangle[t][0] * 2, y1 = rectangle[t][1] * 2;
            int x2 = rectangle[t][2] * 2, y2 = rectangle[t][3] * 2;
            for(int i = x1; i <= x2; i++) {
                for(int j = y1; j <= y2; j++) {
                    map[i][j] = 1;
                }
            }
        }
        
        // 2. 내부 채우기
        for(int t = 0; t < rectangle.length; t++) {
            int x1 = rectangle[t][0] * 2, y1 = rectangle[t][1] * 2;
            int x2 = rectangle[t][2] * 2, y2 = rectangle[t][3] * 2;
            for(int i = x1 + 1; i < x2; i++) {
                for(int j = y1 + 1; j < y2; j++) {
                    map[i][j] = 2;
                }
            }
        }
        
        map[characterX * 2][characterY * 2] = 3;
        
        func(0, characterX * 2, characterY * 2, itemX * 2, itemY * 2, map);
        
        return answer / 2; // 좌표계를 2배로 늘렸으니 값을 리턴할 때는 2로 나눠줘야 함
    }
    
    // DFS
    public void func(int count, int cx, int cy, int ix, int iy, int[][] map) {
        if(cx == ix && cy == iy) {
            answer = Math.min(answer, count);
            map[cx][cy] = 1; // 다음 방문을 위해 1로 갱신
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            int dx = cx + dir[i][0];
            int dy = cy + dir[i][1];
            
            if(map[dx][dy] != 1) {
                continue;
            }
            
            map[dx][dy] = 3; // 방문 표시
            func(count + 1, dx, dy, ix, iy, map);
        }
    }
    
    public void print(int[][] map) {
        int x = map.length;
        int y = map[0].length;
        
        for(int i = y - 1; i >= 0; i--) {
            for(int j = 0; j < x; j++) {
                System.out.printf("%d ", map[j][i]);
            }
            System.out.println();
        }
    }
        
}

// 테케 18, 28, 29, 30 실패
// class Solution {
//     static int answer = (int)1e9;
//     static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌 -> 하우상좌
//     static boolean[][] visited;
//     public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
//         int x = 0, y = 0;
//         for(int[] arr : rectangle) {
//             x = Math.max(x, arr[2]);
//             y = Math.max(y, arr[3]);
//         }
        
//         visited = new boolean[x + 2][y + 2]; // 방문배열
//         visited[characterX][characterY] = true; // 시작점
//         func(0, characterX, characterY, itemX, itemY, rectangle, visited);
        
//         return answer;
//     }
    
//     public void func(int count, int cx, int cy, int ix, int iy, int[][] rectangle, boolean[][] visited) {
//         if(cx == ix && cy == iy) { // 아이템 도착
//             visited[cx][cy] = false; // 반대방향을 위해 false로 갱신
//             answer = Math.min(answer, count);
//             return;
//         }
        
//         // 경계선 겹치는 사각형 개수 구하기
//         int cnt = 0;
//         int[] idx = new int[2]; // 최대 2개만 겹침
//         for(int i = 0; i < rectangle.length; i++) {
//             if(check(cx, cy, rectangle[i])) {
//                 idx[cnt++] = i; // 몇 번째 사각형이 겹치는지 기록
//             }
//         }

//         for(int i = 0; i < 4; i++) { // 현재 위치에서 다음 위치로 이동
//             int dx = cx + dir[i][0];
//             int dy = cy + dir[i][1];

//             if(visited[dx][dy]) { // 이미 방문한 경우
//                 continue;
//             }

//             if(cnt == 1) { // 사각형 1개 ~ 다음 이동도 해당 사각형의 테두리에 속해야 됨
//                 if(!check(dx, dy, rectangle[idx[0]])) {
//                     continue;
//                 }   
//             } else { // 사각형 2개 ~ 두 사각형 중 새로운 사각형의 테두리에만 속해야 됨 ~ 내부체크를 위해 rangeCheck 사용
//                 if(rangeCheck(dx, dy, rectangle[idx[0]]) == rangeCheck(dx, dy, rectangle[idx[1]])) {
//                     continue;
//                 }
//             }

//             visited[dx][dy] = true;
//             func(count + 1, dx, dy, ix, iy, rectangle, visited);                
//         }
//     }
    
//     // 경계선 겹침 여부
//     public boolean check(int a, int b, int[] rtg) {
//         if((rtg[0] <= a && a <= rtg[2]) && (rtg[1] == b || b == rtg[3])) { // 가로 겹
//             return true;
//         }
        
//         if((rtg[0] == a || a == rtg[2]) && (rtg[1] <= b && b <= rtg[3])) { // 세로 겹
//             return true;
//         }
        
//         return false;
//     }
    
//     // 내부 겹침 여부
//     public boolean rangeCheck(int a, int b, int[] rtg) {
//         if((rtg[0] <= a && a <= rtg[2]) && (rtg[1] <= b && b <= rtg[3])) { // 가로 겹
//             return true;
//         }
        
//         return false;
//     }
// }
