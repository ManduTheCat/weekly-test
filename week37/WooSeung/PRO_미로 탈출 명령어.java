import java.util.*;

class Point {
    int r, c;
    StringBuilder cmd;

    public Point(int r, int c, StringBuilder cmd) {
        this.r = r;
        this.c = c;
        this.cmd = cmd;
    }
}

class Solution {
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static String[] dir = {"d", "l", "r", "u"};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        boolean[][][] visited = new boolean[n+1][m+1][k+1];

        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y, new StringBuilder()));

        while(!q.isEmpty()) {
            Point p = q.poll();
            
            if(p.r == r && p.c == c && p.cmd.length() == k) {
                return p.cmd.toString();
            }
            
            for(int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if(nr < 1 || nc < 1 || nr > n || nc > m) {
                    continue;
                }

                if(p.cmd.length() < k && !visited[nr][nc][p.cmd.length() + 1]) {
                    visited[nr][nc][p.cmd.length() + 1] = true;
                    StringBuilder newPath = new StringBuilder(p.cmd).append(dir[d]);
                    q.add(new Point(nr, nc, newPath));
                }
            }
        }

        return "impossible";
    }
}
