import java.util.*;

// 구명보트는 최대 2명이 탈 수 있다. 
// people는 최대 50,000까지 주어진다. 

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        int idx = 0;
        
       for(int i = people.length - 1; i >= idx; i--) {
           // 가장 무게가 많이 나가는 사람과 적게 나가는 사람을 더 했을때 limit와 값이 같거나 작다면 보트를 태운다.
           if(people[i] + people[idx] <= limit) {
               answer++;
               idx++;
           }
            // limit를 넘었다면 가장 무게가 많이 나가는 사람은 보트를 혼자 타야한다.
           else {
               answer++;
           }
           
       }
        
        return answer;
    }
}
