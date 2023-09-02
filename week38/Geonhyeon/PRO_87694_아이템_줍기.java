import java.util.*;
/*
1. 4방탐색을 하는데 이동하고자 하는 좌표가 테두리인지 검사하는 메서드 & 방문체크
    테두리인지 검사 하는 방법 -> 사각형은 최대 4개. 4개의 사각형과 
    (((x == x1) && (y==y1 || y==y2)) || ((y=y1) && (x == x1) || (x==x2)))
    동시에 다른 사각형의 (x1,y1) (x2,y2) 사이에 있으면 안됨
    
    4방향 탐색은 BFS로
    
    예시 첫번째에서 (3,5)에서 바로 (3,6)으로 가버리는 문제발생
    -> 한칸차이로 나타나는 문제니 좌표를 2배로 늘려줘서 해결
*/
class Solution {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int min = Integer.MAX_VALUE; //answer
    static boolean[][] visited = new boolean[102][102];
    static int[][] rectangles; //rectangle global
    static int targetX; //itemX global
    static int targetY; //itemY global
    
    public int solution(int[][] rectangle, int characterX
                        , int characterY, int itemX, int itemY) {
        rectangles = new int[rectangle.length][4];
        for(int i=0;i<rectangle.length;i++){
            for(int j=0;j<rectangle[i].length;j++){
                rectangles[i][j] = rectangle[i][j]*2;
            }
        }
        // rectangles = rectangle;
        targetX = itemX*2;
        targetY = itemY*2;
      
        bfs(characterX*2,characterY*2);
        int answer = min/2;
        return answer;
    }

    /* 최단거리 -> DFS보단 BFS로 진행 */
    static void bfs(int charX,int charY){
        Queue<Point> q = new LinkedList<>();
        Point p = new Point(0,charX,charY);
        visited[charX][charY] = true;
        q.offer(p);
        while(!q.isEmpty()){
            p = q.poll();
            // System.out.println("[Loop] x:"+p.x+" y:"+p.y+" cnt:"+p.cnt);
            if(p.x == targetX && p.y == targetY){
                // System.out.print("[갱신]: "+p.cnt);
                min = Math.min(min,p.cnt);
                break;
            }
            //4way search
            for(int pos=0;pos<4;pos++){
                int nx = p.x+dx[pos];
                int ny = p.y+dy[pos];
                if(isRange(nx,ny)&& !visited[nx][ny]
                   && isBorder(nx,ny) && !isInside(nx,ny)){
                    // System.out.println("[큐에담음] ny:"+nx+" ny:"+ny);
                    visited[nx][ny] = true;
                    q.offer(new Point(p.cnt+1,nx,ny));
                }
            }
        }
        
    }
    
    /* 사각형의 테두리에 있는지 검사. 
    1. 정확히 테두리에 위치해야함
    */
    static boolean isBorder(int x, int y){
        for(int i=0;i<rectangles.length;i++){
            int x1 = rectangles[i][0];
            int y1 = rectangles[i][1];
            int x2 = rectangles[i][2];
            int y2 = rectangles[i][3];
            if( ( (x==x1 || x==x2) && (y1<=y && y<=y2) )
               || ( (y==y1||y==y2) && (x1<=x) && (x<=x2) ) ){
                // System.out.println("Point3: ");
                return true;
            }
        }
        return false;
    }

  /* 다른 사각형의 범위안에 있으면 안됨 즉 x1<x<x2 || y1<y<y2 이면 안됨 */
    static boolean isInside(int x, int y){
        for(int i=0;i<rectangles.length;i++){
            int x1 = rectangles[i][0];
            int y1 = rectangles[i][1];
            int x2 = rectangles[i][2];
            int y2 = rectangles[i][3];
            if(x1<x && x<x2 && y1<y && y<y2) return true;
        }
        return false;
    }
    static boolean isRange(int x, int y){
        if(x>=0 && x<=100 && y>=0 && y<=100 ) return true;
        return false;
    }
}

class Point{
    int cnt;
    int x;
    int y;
    
    public Point(int cnt, int x, int y){
        this.cnt = cnt;
        this.x = x;
        this.y = y;
    }
}
