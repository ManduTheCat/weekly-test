/* 
 * 모든 차량이 단속용 카메라를 한 번은 만나도록 !
 * 최소 몇 대의 카메라 설치 ?
 * routes[i][0] : i번째 차량이 고속도로에 진입한 지점
 * routes[i][1] : i번째 차량이 고속도로에서 나간 지점
 */

/*
 * 탈출 오름차순 정렬
 * 다음 진입이 이전 탈출보다 작다면 추가 설치xxx
 */

import java.util.*;

class Car implements Comparable<Car>{
    int start, end;
    
    Car(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public int compareTo(Car o) {
        if(this.end != o.end) {
            return this.end - o.end;
        } else {
            return this.start - o.start;
        }
    }
    
}
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        List<Car> list = new ArrayList<>();
        for(int[] arr : routes) {
            list.add(new Car(arr[0], arr[1]));
        }
        
        list.sort(Car::compareTo);
        
        int now = -30001;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).start <= now){
                continue;
            } else {
                now = list.get(i).end;
                answer++;
            }
            
        }
        
        return answer;
    }
}
