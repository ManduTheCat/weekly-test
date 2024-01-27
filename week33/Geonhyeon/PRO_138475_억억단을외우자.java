import java.util.*;
class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        Point[] dp = new Point[e+1];
        
        for(int i=0; i<=e; i++){
            dp[i] = new Point(i, 0);
        }
        
        //개수세기
        for(int i=1;i<=e;i++){
            for(int j=i;j<=e;j+=i){
                dp[j].cnt++;
            }            
        }

        //정렬. cnt내림차순 num오름차순
        Arrays.sort(dp,(p1,p2) ->{
            if(p2.cnt > p1.cnt) return 1;
            else if(p2.cnt < p1.cnt) return -1;
            else{
                if(p2.num < p1.num) return 1;
                else return -1;
            }
        });
        
        for(int i=0;i<starts.length;i++){
            int s = starts[i];
            for(int j=0;j<=e;j++){
                if(dp[j].num >= s) {
                    answer[i] = dp[j].num;
                    break;
                }
            }
        }
        return answer;
    }
}


class Point{
    int num;
    int cnt;
    
    Point(int num, int cnt){
        this.num = num;
        this.cnt = cnt;
    }
}
