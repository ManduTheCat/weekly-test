import java.util.*;
/*
근무태도를 기준으로 내림차순 정렬

이때 근무태도(scores[n][0])가 같으면 동료평가점수(scores[n][1])를 기준으로 오름차순 정렬 (동료평가점수를 오름차순으로 해야 {3,2} {3,4} 이렇게 두 원소가 있다고 할 때 {3,2}가 성과급명단에 포함됨. 내림차순으로 해버릴시 {3,4}때문에 {3,2}가 제외되어버린다.)

이렇게 정렬후 배열을 탐색. 여기서 항상 다음에 나오는 원소는 그 전의 원소보다 근무태도(scores[n][0]) 점수가 같거나 낮음.

maxScore값을 비교해 현재 maxScore보다 높으면 갱신

따라서 maxScore보다 낮으면 원호인지 검사

해당 조건을 통과하면 원호보다 높은 점수면 cnt+1
*/
class Solution {
    public int solution(int[][] scores) {    
        int wonhoScore = scores[0][1]+scores[0][0];
        int[] wonho = {scores[0][0],scores[0][1]};
        
        
        Arrays.sort(scores, (a,b) -> {
            if(b[0] == a[0]){
                return a[1]-b[1];
            }else if(b[0]>a[0]){
                return 1;
            }else{
                return -1;
            }
        });
        
        int maxScore = Integer.MIN_VALUE;
        int cnt = 0;
        
        for(int i=0;i<scores.length;i++){
            if(maxScore <scores[i][1]){
                maxScore = scores[i][1];
            } else if( maxScore > scores[i][1]){
                if(scores[i][0] == wonho[0] && scores[i][1] == wonho[1]){
                    return -1;
                }
                continue;
            }
            
            if((scores[i][0] + scores[i][1]) > wonhoScore){
                cnt++;
            }
        }
        
        
        
        return cnt+1;
    }
}
