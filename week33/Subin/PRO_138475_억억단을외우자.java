class Solution {
    public int[] solution(int e, int[] starts) {
        // 1부터 e까지 억억단에 등장한 횟수 구하기
        int[] cnt = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            ++cnt[i];
            for (int j = i; j <= e; j += i) ++cnt[j];
        }
        
        // s부터 e의 범위 중 최댓값 구하기 (dp[i]: i ~ e 중 최댓값, 여러개라면 가장 작은 수)
        int[] dp = new int[e + 1];
        dp[e] = e;
        for (int i = e - 1; i > 0; i--) {
            dp[i] = cnt[i] < cnt[dp[i + 1]] ? dp[i + 1] : i;
        }
        
        // 정답 구하기
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) answer[i] = dp[starts[i]];
        return answer;
    }
    
}
