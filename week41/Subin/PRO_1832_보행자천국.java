class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        // init
        int[][][] dp = new int[m][n][2];
        for (int r = 1; r < m; r++) {
            if (cityMap[r][0] == 1) break;
            dp[r][0][0] = 1;
        }
        for (int c = 1; c < n; c++) {
            if (cityMap[0][c] == 1) break;
            dp[0][c][1] = 1;
        }
        
        // dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (cityMap[i][j] == 1) continue;

                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                
                if (cityMap[i - 1][j] == 2) dp[i][j][0] = dp[i - 1][j][0];
                if (cityMap[i][j - 1] == 2) dp[i][j][1] = dp[i][j - 1][1];
            }
        }
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}
