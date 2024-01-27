/*
 * 어피치 n발 -> 라이언 n발
 * 과녁 점수는 10 ~ 0점
 * k점에 더 많은 화살을 맞춘 선수가 k점 획득. 같은 수의 화살을 맞춘 경우 어피치가 획득
 * 최종 점수가 더 높은 선수가 우승자. 최종 점수가 같은 경우 어피치가 우승
 
 * 현재 상황은 어피치가 n발을 다 쏜 후, 라이언의 차례
 * 어피치를 가장 큰 점수 차이로 이기기 위해 어떻게 n발을 쏴야하는지 ?
 * 우승할 수 없는 경우 return -1
 * 우승 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 return
 */

class Solution {
    static int score = -(int)1e9; // 점수 차이
    static int[] answer; // 최종 라이언 우승 방법
    public int[] solution(int n, int[] info) {
        
        int[] arr = new int[11];
        func(n, 0, info, arr);
        
        if(answer == null) answer = new int[]{-1}; // 라이언이 우승할 방법이 없는 경우
        
        return answer;
    }
    
    void func(int n, int idx, int[] info, int[] arr) {
        if(n == 0) { // 모든 화살 분배 완료
            calc(info, arr); // 점수 구하기
            return;
        }
        
        if(idx > 10) { // 배열을 벗어나면 그만 !
            return;
        }
        
        // 화살 분배
        for(int i = 0; i <= n; i++) {
            arr[idx] = n - i; // 10 - idx점에 n - i개의 화살을 맞힌 경우
            func(i, idx + 1, info, arr);
        }
    }
    
    void calc(int[] info, int[] arr) {
        int a = 0, b = 0; // 어피치(a), 라이언(b)
        
        for(int i = 0; i <= 10; i++) {
            if(info[i] + arr[i] == 0) { // 둘 다 10 - i점에 맞히지 못한 경우
                continue;
            }
            
            if(info[i] < arr[i]) { // 라이언이 10 - i점을 얻는 경우
                b += 10 - i;
            } else { // 어피치가 10 - i점을 얻는 경우
                a += 10 - i;
            }
        }
        
        if(b > a) { // 라이언 우승
            if(b - a > score) { // 점수 차이가 갱신되는 경우
                score = b - a;
                answer = arr.clone(); // 해당 점수를 얻는 배열로 우승 방법 갱신
            } else if(b - a == score) { // 점수 차이가 같은 경우 가장 낮은 과녁을 많이 맞힌 배열 return
                for(int i = 10; i >= 0; i--) {
                    if(arr[i] > answer[i]) { // 새로운 우승 방법이 조건에 부합
                        answer = arr.clone();
                        break;
                    } else if(arr[i] < answer[i]) { // 기존 우승 방법이 조건에 부합
                        break;
                    }
                }
            }
        }
    }
}
