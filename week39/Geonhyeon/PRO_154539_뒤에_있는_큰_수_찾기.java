import java.util.*;
/*
그냥 했을 때 -> O(n^2)이기 때문에 21~23번 테케에서 시간초과
O(n)으로 탐색하는 방법. i의 뒤를 탐색하지 않고 스택에 넣어서 i보다 인덱스가 작은 요소들의
뒷 큰수를 정하는 방식.
*/
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer,-1);
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<numbers.length;i++){
            //스택이 비거나 나보다 크거나 같은 수가 나올 떄까지 
            //numbers[stack.pop()) = numbers[i]로 초기화
            while(!stack.isEmpty() && numbers[i] > numbers[stack.peek()]){
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i);
        }
        return answer;
    }
}

