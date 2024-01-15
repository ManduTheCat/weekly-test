/*
 * 크기가 모두 같은 1 ~ n번 상자가 컨테이너 벨트에 일렬로 놓여 있음
 * 일방향 진행으로 벨트에 놓인 순서대로(1번부터) 상자 내리기 가능
 * 배송 순서에 맞게 택배상자를 실어야 함
 * 맨 앞에 놓인 상자가 순서에 맞지 않으면 순서가 될 때까지 보조 컨테이너 벨트에 저장
 * 보조 컨테이너 벨트는 입구 = 출구 ~ 스택
 * 보조 컨테이너를 이용해서 원하는 순서대로 상자를 싣지 못하면, 거기서 stop
 * 몇 개의 상자를 실을 수 있는지 구하기 ~
 */

import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>(); // 보조
        
        int num = 1;
        int idx = 0;
        
        while(idx < order.length && num <= order.length) {
            if(num == order[idx]) { // 1. 메인 컨테이너 == 순서
                answer++;
                idx++;
                num++;
                
                if(num > order.length) { // 1-2. 마지막 순서인 경우 보조 컨테이너 탐색 why ? 메인 컨테이너 다 비었음;;;
                    while(!stack.isEmpty() && idx < order.length) {
                        if(stack.peek() == order[idx]) {
                            answer++;
                            idx++;
                            stack.pop();
                        }
                    }
                }
            } else { // 2. 메인 컨테이너 != 순서
                if(!stack.isEmpty()) {
                    if(stack.peek() == order[idx]) { // 2-1. 보조 컨테이너 == 순서
                        answer++;
                        idx++;
                        stack.pop();
                    } else { // 2-2. 보조 컨테이너 != 순서
                        stack.push(num++);
                    }
                } else { // 2-3. 보조 컨테이너 Empty
                    stack.push(num++);
                }
            }

        }
        
        return answer;
    }
}
