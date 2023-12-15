class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int[][][] dp = new int[m + 1][n + 1][2];
        // 0은 오른쪽, 1은 아래
        dp[1][1][0] = dp[1][1][1] = 1;
        
        for(int r = 1; r <= m; r++) {
            for(int c = 1; c <= n; c++) {
                if(cityMap[r - 1][c - 1] == 0) {
                    dp[r][c][0] += (dp[r][c - 1][0] + dp[r - 1][c][1]) % MOD;
                    dp[r][c][1] += (dp[r][c - 1][0] + dp[r - 1][c][1]) % MOD;
                }else if(cityMap[r - 1][c - 1] == 2) {
                    dp[r][c][0] += dp[r][c - 1][0] % MOD;
                    dp[r][c][1] += dp[r - 1][c][1] % MOD;
                }
            }
        }
        
        return dp[m][n][1];
    }
}
