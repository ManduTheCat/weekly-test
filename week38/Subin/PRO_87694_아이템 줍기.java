import java.util.*;

class Solution {
    private int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[101][101];
        for(int[] r : rectangle) fill(map, r[0] * 2, r[1] * 2, r[2] * 2, r[3] * 2);
        
        return getPath(characterX, characterY, itemX, itemY, map);
    }

    public void fill(int[][] map, int lx, int ly, int rx, int ry) {
        for(int i = ly; i <= ry; i++) {
            for(int j = lx; j <= rx; j++) {
                if(map[i][j] == 2) continue;

                map[i][j] = 2;
                if(i == ly || i == ry || j == lx || j == rx) {
                    map[i][j] = 1;
                }
            }
        }
    }
    
    private int getPath(int characterX, int characterY, int itemX, int itemY, int[][] map) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[101][101];
        
        q.add(new int[]{characterY * 2, characterX * 2});
        visited[characterY * 2][characterX * 2] = true;
        
        int answer = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            ++answer;

            while (size-- > 0) {
                int[] cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int ny = cur[0] + dir[i][0];
                    int nx = cur[1] + dir[i][1];

                    if (ny == itemY * 2 && nx == itemX * 2) return answer / 2;
                    
                    if (ny < 0 || ny > 100 || nx < 0 || nx > 100 || visited[ny][nx] || map[ny][nx] != 1) continue;

                    q.add(new int[]{ny, nx}) ;
                    visited[ny][nx] = true;
                }
            }
        }

        return 0;
    }

}
