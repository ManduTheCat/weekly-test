/*
 * m x n 격자 city_map
 * 오른쪽 or 아래 방향으로 한 칸씩 이동 가능
 * 이동 가능(0), 통행 금지(1), 좌/우회전 금지(2) ~ 이전 이동 방향에 따라 이동 방향 고정
 * (출발점 -> 도착점까지 이동 가능한 전체 경로 수) % 20170805
 */

class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        
        int[][][] dp = new int[m][n][2]; // 상(0), 좌(1) 따로
        
        // 0행 이동 고정
        for(int i = 0; i < n; i++) {
            if(cityMap[0][i] == 1) {
                break;
            }
            dp[0][i][1] = 1;
        }
        
        // 0열 이동 고정
        for(int i = 0; i < n; i++) {
            if(cityMap[i][0] == 1) {
                break;
            }
            dp[i][0][0] = 1;
        }
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                // 이동 불가능한 경우
                if(cityMap[i][j] == 1) {
                    continue;
                }
                
                // 이전 경로가 0인지 2인지 따져서 상/좌 경우의 수 더하기
                // MOD 주의
                // 위쪽에서 올 때 2인 경우, 위 -> 아래 방향만 가능
                // 왼쪽에서 올 때 2인 경우, 왼 -> 오른 방향만 가능
                dp[i][j][0] += (cityMap[i - 1][j] == 0 ? dp[i - 1][j][0] +  dp[i - 1][j][1] : dp[i - 1][j][0]) % MOD;
                dp[i][j][1] += (cityMap[i][j - 1] == 0 ? dp[i][j - 1][0] + dp[i][j - 1][1] : dp[i][j - 1][1]) % MOD;
            }
        }
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
    
    public void print(int[][][] dp) {
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                System.out.printf("[%d, %d] ", dp[i][j][0], dp[i][j][1]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
