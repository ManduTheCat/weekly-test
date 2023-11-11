import java.util.*;

class Solution {
    static int min = Integer.MAX_VALUE;
    
    public int solution(int N, int number) {
        dfs(0,0,N,number);
        return (min==Integer.MAX_VALUE)?-1:min;
    }
    
    public void dfs(int depth, int current, int N, int number){
        if(depth>8) return;
        if(current == number) min = Math.min(depth,min);
        int temp = 0;
        for (int i = 0; i < 8; i++) {
            if (depth + i < 8) {
                temp = temp * 10 + N;
                dfs(depth + i + 1, current + temp,N, number);
                dfs(depth + i + 1, current - temp, N, number);
                dfs(depth + i + 1, current / temp, N, number);
                dfs(depth + i + 1, current * temp, N, number);     
            }      
        }
    }
}

