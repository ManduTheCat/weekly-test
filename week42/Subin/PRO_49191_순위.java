import java.util.*;

class Solution {
    
    private static final int INF = (int) 1e9;
    
    public int solution(int n, int[][] results) {
        
        int[][] dis = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dis[i], INF);
            dis[i][i] = 0;
        }
        
        for (int[] result : results) {
            dis[result[0]][result[1]] = 1;
        }
        
        // 플로이드-워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j || j == k || k == i) continue;
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (dis[i][j] != INF || dis[j][i] != INF) {
                    ++cnt;
                }               
            }
            
            if (cnt == n) ++answer;
        }
        
        return answer;
    }
}
