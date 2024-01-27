import java.util.*;

/*
* 투포인터
* length = 1000001로 해줘야 답의 길이가 1000000인 경우도 통과한다.
* sum > k 이면 left를 빼주고 오른쪽으로 이동
* sum < k 이면 right를 오른쪽으로 이동하고 더해주기
* sum == k 이면 left와 right의 차를구해 +1을 한뒤 최소길이인지 판별. 이후 left오른쪽으로 이동.
*/

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int left = 0;
        int right = 0;
        int sum = sequence[right];
        int length = 1000001;
        
        while(right < sequence.length){
            if(sum > k){ // 넘칠 때. left 증가
                sum -= sequence[left];
                left++;
            }else if(sum < k){ // 부족할 때. 더 더해줌 -> right 증가
                right++;
                if(right < sequence.length) sum += sequence[right];
            }else{ // k = sum
                if(length > (right-left)+1 ){
                    length = (right-left)+1;
                    answer[0] = left;
                    answer[1] = right;
                }
                sum -= sequence[left];
                left++;
            }
        }
        
        //여전히 뒤를 더 체크해 봐야한다면
        if(sum >= k){
            while(left < right){
                if(sum == k){
                    if(length > right-left ){
                        length = right-left;
                        answer[0] = left;
                        answer[1] = right-1;
                        break;
                    }
                }
                else{
                    sum -= sequence[left];
                }
                left++;
            }
        }
        
        int[] result = {answer[0],answer[1]};
        return result;
    }
}
