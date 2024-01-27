/*
 * pop, insert를 통해 두 큐의 원소 합이 같도록 만들기
 * 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수 구하기
 * 불가능한 경우 return -1
 
 * 불가능한 경우
 * 1. 총 합이 홀수인 경우
 * 2. 목표치보다 큰 수가 있는 경우
 */

class Solution {
    public int solution(int[] q1, int[] q2) {
        int a = 0, b = 0;
        long sumA = 0, sumB = 0;
        
        for(int i = 0; i < q1.length; i++) {
            sumA += q1[i];
            sumB += q2[i];
            
            a = Math.max(a, q1[i]);
            b = Math.max(b, q2[i]);
        }
        
        long sum = sumA + sumB;
        if(sum % 2 == 1 || a > sum / 2 || b > sum / 2) {
            return -1;
        }
        
        int x = 0, y = 0;
        int t = q1.length;
        
        int end = t * 3;
        int answer = 0;
        
        while(sumA != sumB) {
            if(sumA > sumB) { // A -> B
                if(x < t) {
                    sumA -= q1[x];
                    sumB += q1[x++];
                } else {
                    sumA -= q2[x - t];
                    sumB += q2[x++ - t];
                }
            } else { // B -> A
                if(y < t) {
                    sumB -= q2[y];
                    sumA += q2[y++];
                } else {
                    sumB -= q1[y - t];
                    sumA += q1[y++ - t];
                }
            }
            
            answer++;
            
            if(answer > end) { // 최대 작업 횟수 초과 ~ 이를 넘어서면 루프
                return -1;
            }
        }
        
        return answer;
    }
}
