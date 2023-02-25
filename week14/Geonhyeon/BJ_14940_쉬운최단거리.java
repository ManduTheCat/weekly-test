import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 세로
        int m = Integer.parseInt(st.nextToken()); // 가로
        int startRow = -1, startCol = -1;
        int[][] input = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if (input[i][j] == 2) {
                    startRow = i;
                    startCol = j;
                }
            }
        }

        int[][] result = BFS(startRow, startCol, input, n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(result[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static int[][] BFS(int startRow, int startCol, int[][] input, int n, int m) {
        boolean[][] visited = new boolean[n][m];
        visited[startRow][startCol] = true; //방문체크
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(startRow, startCol));
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][] distance = new int[n][m];
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int pos = 0; pos < 4; pos++) {
                int nx = p.x + dx[pos];
                int ny = p.y + dy[pos];
                if (isRange(nx,ny,n,m) && input[nx][ny] != 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                    distance[nx][ny] = distance[p.x][p.y] + 1;
                }
            }
        }

        //갈수있는데 못가는
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && input[i][j] == 1) {
                    distance[i][j] = -1;
                }
            }
        }
        return distance;
    }

    public static boolean isRange(int x, int y, int n, int m) {
        if(x>=0 && x<n && y>=0 && y<m) return true;
        return false;
    }
}
