// 연속 펄스 부분 수열은 연속 부분 수열에 펄스 수열 [1, -1, 1...]을 곱하는 것
// 연속 펄스 부분 수열의 합 중 가장 큰 것을 반환하라

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        boolean flag = true;
        
        long sumA = 0;
        long sumB = 0;
        
        for(int num : sequence) {
            
            sumA += flag ? num : -num;
            sumB += flag ? -num : num;
            
            sumA = Math.max(0, sumA);
            sumB = Math.max(0, sumB);
            
            answer = Math.max(answer, Math.max(sumA, sumB));
            flag = !flag;
        }
        
        return answer;
    }
}
