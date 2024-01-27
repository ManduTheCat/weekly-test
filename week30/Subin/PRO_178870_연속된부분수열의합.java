class Solution {
    public int[] solution(int[] sequence, int k) {
        
        int N = sequence.length;
        int ansL = 0, ansR = N;
        int sum = 0;
        
        for (int l = 0, r = 0; l < N; l++) {
            while (r < N && sum < k) {
                sum += sequence[r++];
            }

            if (sum == k && ansR - ansL > r - l - 1) {
                ansL = l;
                ansR = r - 1;
            }
            
            sum -= sequence[l];
        }
        
        int[] answer = {ansL, ansR};
        return answer;
    }
}
