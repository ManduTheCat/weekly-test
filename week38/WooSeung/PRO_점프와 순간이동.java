// k칸을 앞으로 점프하거나, 현재까지 온 거리 x 2에 해당하는 위치로 순간이동
// 순간이동을 하면 건전지 사용량이 줄지 않는다.
// k칸을 이동하면 k 만큼의 건전지 사용량이 든다.
// n이 짝수이면 순간이동을 홀수이면 n을 1 차감
import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        
        while(n > 0) {
            if(n % 2 == 0) {
                n /= 2;
            }else{
                n--;
                ans++;
            }    
        }
        
        
        return ans;
    }
}
