import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int curCam = -30001;
        
        for(int[] arr : routes) {
            if(curCam < arr[0]) {
                answer++;
                curCam = arr[1];
            }
        }
        
        return answer;
    }
}
