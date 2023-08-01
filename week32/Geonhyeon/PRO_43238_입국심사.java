import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left = 1;
        long right = (long)n*times[times.length-1];
        long mid;
        long answer = right;
        while(left<=right){
            mid = (left+right)/2; // mid = 시간
            long numberOfPeople = 0; // 몇 명을 심사할 수 있는지
            for(int i=0;i<times.length;i++){
                numberOfPeople += mid/times[i];
            }
            
            //sum > n 이면 더 줄일 수 있다는 뜻
            if(numberOfPeople>=n){
                answer = mid;
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        
        return answer;
    }
}
