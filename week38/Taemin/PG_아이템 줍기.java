import java.util.*;

class Solution {
    
    // 좌표 클래스
    static class Index {
        int x;
        int y;
        int move;
        
        public Index(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
    
    // 최소 거리 맵
    public int[][] map;
    
    // 북, 동, 남, 서
    public int[] dy = { 1, 0, -1, 0 };
    public int[] dx = { 0, 1, 0, -1 };
    
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 맵 초기화
        map = new int[51][51];
        for (int i = 0; i <= 50; i++) {
            Arrays.fill(map[i], (int)1e9);
        }
        
        // BFS
        Queue<Index> queue = new LinkedList<>();
        queue.add(new Index(characterX, characterY, 0));
        map[characterX][characterY] = 0;
        
        while(!queue.isEmpty()) {
            Index now = queue.poll();
            //System.out.println(now.x + " " + now.y + " " + now.move);
            
            for (int d = 0; d < 4; d++) {
                int nextX = now.x + dx[d];
                int nextY = now.y + dy[d];
                int nextMove = now.move + 1;
            
                // 사각형의 라인에 위치하지 않은 경우 제외
                if (!isLine(rectangle, now.x, now.y, nextX, nextY)) {
                    //System.out.println("isLine: " + nextX + " " + nextY + " " + nextMove);
                    continue;
                }
                
                // 사각형 면적의 내부에 위치한 경우 제외
                if (isInner(rectangle, now.x, now.y, nextX, nextY)) {
                    //System.out.println("isInner: " + nextX + " " + nextY + " " + nextMove);
                    continue;
                }
                
                // 이동 위치의 거리가 더 짧은 경우 제외
                if (map[nextX][nextY] <= nextMove) {
                    //System.out.println("nextMove: " + nextX + " " + nextY + " " + nextMove);
                    continue;
                }
                
                // 모두 통과 시 도착 지점 여부 확인
                if (nextX == itemX && nextY == itemY) map[nextX][nextY] = nextMove;
                else {
                    queue.add(new Index(nextX, nextY, nextMove));
                    map[nextX][nextY] = nextMove;
                }
            }
        }
        
        return map[itemX][itemY];
    }
    
    // 특정 사각형의 라인 위에 위치한 좌표 판단
    public boolean isLine(int[][] rectangle, int fromX, int fromY, int toX, int toY) {
        boolean result = false;
        for (int[] r : rectangle) {
            int x1 = r[0];
            int y1 = r[1];
            int x2 = r[2];
            int y2 = r[3];
            
            if ((x1 == fromX && x1 == toX && (y1 <= fromY && fromY <= y2) && (y1 <= toY && toY <= y2)) ||
                (x2 == fromX && x2 == toX && (y1 <= fromY && fromY <= y2) && (y1 <= toY && toY <= y2)) ||
                (y1 == fromY && y1 == toY && (x1 <= fromX && fromX <= x2) && (x1 <= toX && toX <= x2)) ||
                (y2 == fromY && y2 == toY && (x1 <= fromX && fromX <= x2) && (x1 <= toX && toX <= x2))) {
                result = true;
                break;
            }
        }
        
        return result;
    }
    
    // 특정 사각형의 내부에 위치한 좌표 판단
    public boolean isInner(int[][] rectangle, double fromX, double fromY, double toX, double toY) {
        boolean result = false;
        for (int[] r : rectangle) {
            int x1 = r[0];
            int y1 = r[1];
            int x2 = r[2];
            int y2 = r[3];
            
            double x = (fromX + toX) / 2.0;
            double y = (fromY + toY) / 2.0;
            
            // 어떤 사각형 내부로 이동하는 경우
            if ((x1 < x && x < x2) && (y1 < y && y < y2)) {
                result = true;
                break;
            }
        }
        
        return result;
    }
}
