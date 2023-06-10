/*
 * 1대 1 푸드파이트, 매 대결마다 음식의 종류와 양이 바뀜
 * 음식 일렬 배치한 후 A선수는 왼->오, B선수는 오->왼
 * 중앙에는 물을 배치하고, 물을 먼저 먹는 선수가 승리
 * 공정성을 위해 음식의 종류와 양이 같고, 먹는 순서도 같아야 함
 * 칼로리가 낮은 음식부터 먼저 먹을 수 있게 배치
 
 * food :  음식의 양을 칼로리가 적은 순서대로 나타내는 정수 배열
 * food[0] : 물, food[i] :  i번 음식의 수
 * return : 대회를 위한 음식의 배치를 나타내는 문자열
 */

/*
 * 1. 최대 짝수개
 * 2. 0
 * 3. reverse Stack
 */

import java.util.Stack;
class Solution {
    public String solution(int[] food) {
        String answer = "";
        
        Stack<Integer> stack = new Stack<>();
        for(int i = 1; i < food.length; i++) {
            for(int j = 0; j < food[i] / 2; j++) {
                answer += i;
                stack.push(i);
            }
        }
        
        answer += 0;
        
        while(!stack.empty()) {
            answer += stack.pop();
        }
        
        return answer;
    }
}

// 코드는 간결하나 String 객체 생성이 많아서 기존보다 살짝 오래걸림
// class Solution {
//     public String solution(int[] food) {
//         String answer = "0";

//         for (int i = food.length - 1; i > 0; i--) {
//             for (int j = 0; j < food[i] / 2; j++) {
//                 answer = i + answer + i; 
//             }
//         }

//         return answer;
//     }
// }

// StringBuilder 사용해서 실행 시간 매우 빠름
// 위 방법들은 nn ms, 이건 n ms
// class Solution {
//     public String solution(int[] food) {
//         StringBuilder builder = new StringBuilder();
//         for (int i=1; i<food.length; i++) {
//             int result = food[i] / 2;
//             builder.append(String.valueOf(i).repeat(result));
//         }
//         String answer = builder + "0";
//         return answer + builder.reverse();
//     }
// }
