/*
 * 1. 자신보다 뒤에
 * 2. 자신보다 크면서
 * 3. 가장 가까이
 * 없으면 -1
 */

import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int length = numbers.length;
        int[] answer = new int[length];

        Arrays.fill(answer, -1);
        
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        
        for(int i = 1; i < length; i++) {
            if(numbers[stack.peek()] < numbers[i]) { // 상승곡선 ~ 뒷 큰수 갱신
                while(!stack.empty() && numbers[stack.peek()] < numbers[i]) { // 뒷 큰수 조건 충족성공할 때까지만 갱신
                    answer[stack.pop()] = numbers[i];
                }
            }
                // 하강곡선일 때는 뒷 큰수 갱신 불가능
                stack.push(i); // 인덱스 저장
            
        }

        return answer;
    }
    
    void print(int[] arr) {
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
