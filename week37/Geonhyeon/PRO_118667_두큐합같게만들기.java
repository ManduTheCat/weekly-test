import java.util.*;
/*
# 1번째 시도
굳이 Queue자료구조를 만들어야하나? -> 써야한다. 내가 줬던원소를 다시 받을 수도 있기때문
1. 두 큐의 합/2 구하기 => target
2. 우선 한큐에 다 몰아넣고 하나씩 빼면서 target이 되는지 검사
3. 탐색후 결과반환
*/

/* 
# 2번째 시도
그냥 큐만들어서 하자... 
최악의 경우 이동횟수 -> 큐의길이 * 3이다. 
왜냐하면 제일큰수하나와 나머지합이 같다고 하고 그 제일 큰 수가 큐의 제일 마지막에 있으면 (큐의길이*3)-1 회 걸리기 때문
*/
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        int count = 0;
        long q1Sum = 0,q2Sum =0, target = 0;
        for(int i=0;i<queue1.length;i++){
            q1Sum += (long)queue1[i];
            q2Sum += (long)queue2[i];
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        
        while(true){
            if(q1Sum == q2Sum) break;
            if(count >= queue1.length*3 ) break;
            if(q1Sum > q2Sum){
                int num = q1.poll();
                q1Sum -= num;
                q2Sum += num;
                q2.offer(num);
            }else{
                int num = q2.poll();
                q2Sum -= num;
                q1Sum += num;
                q1.offer(num);
            }
            count++;
        }
        
        
        
        return q2Sum==q1Sum?count:-1;
    }
}
