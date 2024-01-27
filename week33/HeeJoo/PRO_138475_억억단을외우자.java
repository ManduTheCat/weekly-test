/*
 * 적당한 수 e
 * e 이하의 임의의 수 s 여러 개
 * 각 s에 대해서 s보다 크거나 같고, e보다 작거나 같은 수 중에서 억억단에서 가장 많이 등장한 수 구하기
 * 가장 많이 등장한 수가 여러 개라면 그 중 가장 작은 수로 답하기
 
 * 구구단 표에서 s 이상, e 이하의 숫자 중 가장 많이 등장한 수 ...
 */

import java.util.*;

class Number implements Comparable<Number>{
    int a; // 숫자
    int b; // 약수의 개수
    
    Number(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public int compareTo(Number o) {
        return o.b - this.b;
    }
}
class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        
        Number[] count = new Number[e + 1]; // 1 ~ e의 억억단 출현 횟수
        
        for(int i=0; i<=e; i++){
            count[i] = new Number(i, 0);
        }
        
        // 각 수의 약수의 갯수 구하기
        // 숫자 n을 만드는 곱셈을 구하면 됨
        for (int i = 2; i <= e; i++) { // 2 ~ e // 원래는 1부터 해야되지만 1 * j의 경우 모든 수가 포함하고 있기에 제외
            for (int j = 1; j <= e / i; j++) { // 1 ~ e / i
                count[i * j].b++;
            }
        }

        // 이 방법으로 하면 약수 개수 구하는 시간 엄청걸림 ... ㅠㅅㅠ
        // 개수만 구하면 되니까 위의 방법 사용
        // for(int i = 1; i <= e; i++) {
        //     for(int j = 1; j <= Math.sqrt(i); j++) {
        //         if(i % j == 0) {
        //             if(i / j == j) {
        //                 count[i].b++;
        //             } else {
        //                 count[i].b += 2;
        //             }
        //         }
        //     }
        // }
        
        // starts를 탐색 최적화를 위해
        // 출현횟수 내림차순, 인덱스 오름차순 정렬
        Arrays.sort(count);
        
        for(int i = 0; i < starts.length; i++) {
            for(Number n : count) {
                if(n.a >= starts[i]) { // 처음으로 s보다 작은 수가 나오면 정답
                    answer[i] = n.a;
                    break;
                }
            }
        }
        
        return answer;
    }
}
