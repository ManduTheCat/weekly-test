import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        
        long answer = 0;
        int length = sequence.length;
        int[] plus = Arrays.copyOfRange(sequence, 0, length);
        int[] minus = Arrays.copyOfRange(sequence, 0, length);
        
        converted(0, plus);
        converted(1, minus);
        
        long[] dpPlus = new long[length];
        long[] dpMinus = new long[length];
        dpPlus[0] = plus[0];
        dpMinus[0] = minus[0];
        
        answer = Math.max(dpPlus[0], dpMinus[0]);
        // 현재 포인트한 곳부터 다시 작할 것인지, 이전 누적최대값과 같이 갈 것인지 선택
        for (int i = 1; i < length; i++) {
            dpPlus[i] = Math.max(dpPlus[i-1] + plus[i], plus[i]);
            dpMinus[i] = Math.max(dpMinus[i-1] + minus[i], minus[i]);
            answer = Math.max(answer, Math.max(dpPlus[i], dpMinus[i]));
        }
        
        return answer;
    }
    
    public void converted(int k, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (k == 0) {
                if (i % 2 == 1) array[i] = -array[i];
            }
            else {
                if (i % 2 == 0) array[i] = -array[i];
            }
        }
    }
}
