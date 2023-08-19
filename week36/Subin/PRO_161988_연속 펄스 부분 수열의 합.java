class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        int[] newSequence = new int[n];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) newSequence[i] = sequence[i];
            else newSequence[i] = -sequence[i];
        }
        return getAnswer(newSequence);
    }
    
    private long getAnswer(int[] sequence) {
        int n = sequence.length;
        long[] dp1 = new long[n];
        long[] dp2 = new long[n];
        
        dp1[0] = sequence[0];
        dp2[0] = -sequence[0];
        
        long answer = Math.max(dp1[0], dp2[0]);
        for (int i = 1; i < n; i++) {
            dp1[i] = Math.max(dp1[i - 1] + sequence[i], sequence[i]);
            dp2[i] = Math.max(dp2[i - 1] - sequence[i], -sequence[i]);
            answer = Math.max(answer, Math.max(dp1[i], dp2[i]));
        }
        
        return answer;
    }
}
