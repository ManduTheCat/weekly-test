class Solution {
    
    public int MOD = 20170805;
    public long[][][] table;
    
    public int solution(int m, int n, int[][] cityMap) {
        table = new long[m][n][2];
        
        for (int r = 1; r < m; r++) {
            if (cityMap[r][0] == 0 || cityMap[r][0] == 2) {
                table[r][0][1] = 1;
            } 
            else break;
        }
        
        for (int c = 1; c < n; c++) {
            if (cityMap[0][c] == 0 || cityMap[0][c] == 2) {
                table[0][c][0] = 1;
            }
            else break;
        }
        
        for (int startR = 1; startR < m; startR++) {
            
            int r = startR;
            int c = startR;
            
            checkLeft(cityMap, r, c);
            checkUp(cityMap, r, c);
            
            for (r = startR + 1; r < m; r++) {
                if (cityMap[r][c] == 1) continue;
                checkLeft(cityMap, r, c);
                checkUp(cityMap, r, c);
            }
            
            r = startR;
            for (c = startR + 1; c < n; c++) {
                if (cityMap[r][c] == 1) continue;
                checkLeft(cityMap, r, c);
                checkUp(cityMap, r, c);
            }
        }
        
        int answer = (int)((table[m-1][n-1][0] + table[m-1][n-1][1]) % MOD);
        return answer;
    }
    
    public void checkLeft(int[][] cityMap, int r, int c) {
        if (cityMap[r][c-1] == 0) {
            table[r][c][0] = (table[r][c-1][0] + table[r][c-1][1]) % MOD;
        }
        else if (cityMap[r][c-1] == 2) {
            table[r][c][0] = table[r][c-1][0];
        }
    }
    
    public void checkUp(int[][] cityMap, int r, int c) {
        if (cityMap[r-1][c] == 0) {
            table[r][c][1] = (table[r-1][c][0] + table[r-1][c][1]) % MOD;
        }
        else if (cityMap[r-1][c] == 2) {
            table[r][c][1] = table[r-1][c][1];
        }
    }
}
