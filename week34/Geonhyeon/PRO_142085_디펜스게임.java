/*
1. 한 칸씩 enemy[i]를 누적하면서 탐색
2. 만약에 n보다 커진 경우가 생긴다면
    a. k가 남아 있으면 현재까지 인덱스중 가장 큰 값을 제거 (max)
    여기서 max를 제거한 enemy의 합이 n보다 작으면 계속진행. 아니라면 종료
    b. k가 남아있지않다면 그대로 종료
    
*/
/* 두 번째 방법
1. 우선순위 큐에 하나씩 담는다. 이때에 sum으로 넣은 값들을 누적
2. 만약에 sum이 n보다 커지면
    a. k가 남아있지 않다면 종료
    b. k가 남아있다면 하나 빼고 pq에서 하나 빼고 그대로 진행
*/
import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        int answer = 0;
        int sum = 0;
        int max = 0;
        int idx = 0;
        
        for(int i=0;i<enemy.length;i++){
            sum += enemy[i]; //sum에 누적
            pq.offer(enemy[i]);
            if(sum > n){
                if(k>0){ //무적권 사용여부
                    k--;
                    sum -= pq.poll(); //가장 큰 값을 빼주기
                }else{
                    break;
                }
            }
            answer++;
        }
        return answer;
    }
}
