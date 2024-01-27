import java.util.*;

class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] set = new HashSet[8]; // 8보다 크면 -1
        for (int i = 0; i < 8; i++) set[i] = new HashSet<>();
        
        set[0].add(N);
        if (set[0].contains(number)) return 1;
        
        for (int i = 1; i < 8; i++) {
            set[i].add(ns(N, i + 1));
            for (int j = 0; j < i; j++) calculate(set[i], set[j], set[i - j - 1]);
            
            if (set[i].contains(number)) return i + 1;
        }
        
        return -1;
    }
    
    private void calculate(Set<Integer> result, Set<Integer> set1, Set<Integer> set2) {
        for (int num1 : set1) {
            for (int num2 : set2) {
                result.add(num1 + num2);
                result.add(num1 - num2);
                result.add(num1 * num2);
                if (num2 != 0) result.add(num1 / num2);
            }
        }
    }
    
    private int ns(int N, int size) {
        int ret = 0;
        while (size-- > 0) {
            ret *= 10;
            ret += N;
        }   
        return ret;
    }
}



/*

    dp[1]: N
    dp[2]: NN, N+N, N-N, N/N, N*N // NN / dp[1]dp[1]
    dp[3]: NNN, NN + N, N + NN, (N+N)+N, N+(N+N), NN - N, N - NN, (N-N)-N, N-(N-N), NN / N, N / NN, (N/N)/N, N/(N/N), NN * N, N * NN, (N*N)*N, N*(N*N) -> NNN / dp[1]dp[2] / dp[2]dp[1]
    dp[4]: NNNN / dp[1]dp[3] / dp[2]dp[2] / dp[3]dp[1]

*/
