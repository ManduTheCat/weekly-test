import java.util.*;
/*
수열의 각 요소는 -1,1 둘중 하나가 곱해진다.
1. 수열에 1,-1,1 ... 순으로 곱해진 경우
2. 수열에 -1,1,-1 ... 순으로 곱해진 경우 
3. 1,2 두가지 경우를 모두 고려해 최대 수열 찾기

수의 합 구하는 방법
1. 수를 누적시킨다. 음수이면 그 다음 수부터 다시 시작
*/
class Solution {
    static long max = Long.MIN_VALUE;
    public long solution(int[] sequence) {
        makeSequence(sequence, 0);
        makeSequence(sequence, 1);
        return max;
    }
    
    static void makeSequence(int[] sequence, int pulse){
        int[] seq = Arrays.copyOf(sequence,sequence.length);
        //수열 만들기
        for(int i=0;i<seq.length;i++){
            if(i%2 == pulse){
                seq[i] = seq[i]*-1;
            }
        }
        
        int idx = 0;
        long sum = 0; // 100,000 * 500,000 이면 500,000,000,000 이므로 정수범위 초과
        while(idx<seq.length){
            sum += seq[idx];
            if(max < sum) max = sum;
            if(sum < 0) sum = 0;
            idx++;
        }
    }
}
