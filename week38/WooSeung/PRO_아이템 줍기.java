// character의 x, y 좌표값에서 item x, y 좌표값으로 이동해야 한다.
// 이동은 직사각형의 변을 따라 이동하는데 직사각형들은 꼭짓점에서 만나거나, 변이 겹치는 경우는 없다.
// 한 직사각형이 다른 직사각형 안에 완전히 포함되는 경우도 없다.
// 탐색을 쉽게 하기 위해서 맵을 2배로 확장

import java.util.*;

class Point {
    int r, c, cnt;
    public Point(int r, int c, int cnt) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}

class Solution {
    
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int answer;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        map= new int[101][101];
        answer = 0;
        
        for(int[] arr : rectangle) {
            
            int leftX = arr[0] * 2;
            int leftY = arr[1] * 2;
            int rightX = arr[2] * 2;
            int rightY = arr[3] * 2;
            
            for(int i = leftX; i <= rightX; i++) {
                for(int j = leftY; j <= rightY; j++) {
                    if(map[i][j] == 2) {
                        continue;
                    }
                    map[i][j] = 2;
                    if(i == leftX || i == rightX || j == leftY || j == rightY) {
                        map[i][j] = 1;
                    }
                }
            }       
        }
        
        bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        return answer / 2;
    }
    
    static void bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited= new boolean[101][101];
        visited[characterX][characterY] = true;
        q.add(new Point(characterX, characterY, 0));
        
            while(!q.isEmpty()) {
                
                Point p = q.poll();
                
                for(int d = 0; d < 4; d++) {
                    
                    int nr = p.r + dir[d][0];    
                    int nc = p.c + dir[d][1];
                    
                    if(nr < 1 || nc < 1 || nr > 100 || nc > 100) {
                        continue;   
                    }
                    
                    if(itemX == nr && itemY == nc) {
                        answer = p.cnt + 1;
                        return;
                    } 
                    
                    if(map[nr][nc] == 1 && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.add(new Point(nr, nc, p.cnt + 1));
                    }
                    
                }
            }
    }
}
