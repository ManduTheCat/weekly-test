/* 
 * N과 사칙연산만 사용해서 number를 표현할 수 있는 방법 중 N 사용횟수의 최솟값
 * +-x/ 랑 붙이기
 */
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        // digit개의 N으로 만들 수 있는 수의 집합
        // ex) digit = 2 : (5+5, 5-5, 5*5, 5/5, 55)
        // 결과값이 중복되지 않도록 set 사용
        Set<Integer>[] list = new Set[10]; // 1 <= N <= 9

        for(int i = 1; i < 10; i++) {
            list[i] = new HashSet<>();
        }
        
        int num = 0;
        for(int i = 0; i < 9; i++) {
            num += N * Math.pow(10, i); // n, nn, nnn, ...
            list[i + 1].add(num);
        }
        
        for(int digit = 1; digit < 10; digit++) {
            // digit개의 N을 사용하는 연산 : i개의 N (사칙연산) j개의 N
            for(int i = 1 ; i < digit; i++) { // 총 i개의 N 사용
                for(int pre : list[i]) { // pre개의 N 사용
                    for(int post : list[digit - i]) { // post개의 N 사용
                        // 사칙연산
                        list[digit].add(pre + post);
                        list[digit].add(pre - post);
                        list[digit].add(pre * post);
                        if(post != 0) {
                            list[digit].add(pre / post);
                        }
                    }
                }
            }
         // 이미 number가 나온 경우 연산 종료 ...
         // why ? digit : 1, 2, 3, ... 순으로 하기 때문에 최솟값임
         if(list[digit].contains(number)) 
            return digit;
        }
        
        return -1;
    }
}
