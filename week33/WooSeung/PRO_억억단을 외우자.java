// s보다 크거나 같고 e 보다 작거나 같은 수 중에서 억억단에서 가장 많이 등장한 수를 답해야 합니다. 
// 만약 가장 많이 등장한 수가 여러 개라면 그 중 가장 작은 수를 답해야 합니다.


class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        
        int[] cnt = new int[e + 1];
        
        // 배수 세기
        for(int i = 1; i <= e; i++) {
            for(int j = i; j <= e; j += i) {
                cnt[j]++;
            }
        }
        
        // start ~ e 까지의 최대값 구하기
        // cnt값이 동일하면 더 작은 수를 저장
        int[] dp = new int[e + 1];
        dp[e] = e;
        for(int i = e - 1; i > 0; i--) {
            if(cnt[i] < cnt[dp[i + 1]]) {
                dp[i] = dp[i + 1];
            }else {
                dp[i] = i;
            }
        }
        
        for(int i = 0; i < starts.length; i++) {
            answer[i] = dp[starts[i]];
        }
        
        return answer;
    }
}
