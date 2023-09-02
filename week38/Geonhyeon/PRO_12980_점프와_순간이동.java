import java.util.*;
/*두 번째 시도. 홀수일때마다 +1*/
public class Solution {
    public int solution(int n) {
        int cnt = 0;
        while(n>0){
            if(n%2 != 0) cnt++;
            n /= 2;
        }
        return cnt;
    }
}

/*첫 번째 시도. DP로풀기 
    ->효율성테스트 통과실패*/
// public class Solution {
//     public int solution(int n) {
//         int ans = 0;
        
//         int[] dp = new int[n+1];
//         dp[1] = 1;
//         for(int i=2;i<=n;i++){
//             if(i%2 ==0){
//                 dp[i] = Math.min(dp[i-1]+1,dp[i/2]);
//             }else{
//                 dp[i] = Math.min(dp[i-1]+1,dp[i/2]+1);
//             }
//         }

//         // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
//         System.out.println("Hello Java");

//         return dp[n];
//     }
// }

