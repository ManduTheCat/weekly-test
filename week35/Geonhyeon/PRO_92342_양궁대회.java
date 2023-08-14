/*
어피치가 맞춘 개수 + 1개의 화살을 소비할지/안할지 분기
n이 0개가 된다면 점수 최댓값과 비교 갱신, idx가 끝에 다다르면 점수갱신
이때 화살이 남아있으면 가장낮은 점수인 0점에 다 쏟아준다.
*/
import java.util.*;

class Solution {
    static int[] ryan = new int[11]; //과녁 정보를 전역변수로
    static int[] apeach;
    static int MAX = Integer.MIN_VALUE; 
    static int[] answer = {-1};
    
    public int[] solution(int n, int[] info) {
        apeach = info;
        arrow(10,n);        
        return answer;
    }
    
    static void arrow(int idx,int arrow){
        //종료조건. 모든 인덱스 탐색했거나 화살을 모두 소진했을 때
        if(arrow == 0 || idx < 0){
            ryan[10] += arrow; //아직 남은 화살이 있다면 가장 낮은 점수에 맞혀야한다(문제조건)
            int diff = calcScore(); //점수차이 계산
            if(diff>0 && diff>MAX) {
                answer = Arrays.copyOf(ryan,11);
                MAX = diff;
            }
            ryan[10] -= arrow;
            return;
        }
        
        //해당 과녁에 어피치가 쏜 것 +1만큼 화살을 쏠 때. 화살수를 감소시키고 재귀호출
        if(arrow > apeach[idx]){
            ryan[idx] = apeach[idx]+1;
            arrow(idx-1, arrow-(apeach[idx]+1));
            ryan[idx] = 0;
        }
        arrow(idx-1,arrow);//해당 과녁에 안쏘고 다음 인덱스로 가는 경우
    }
    
    /*점수차이 계산*/
    static int calcScore(){
        int diff = 0;
        for(int i=0;i<10;i++){
            if(apeach[i]<ryan[i])
                diff += 10-i;
            else if(apeach[i] > ryan[i])
                diff -= 10-i;
        }
        return diff;
    }
}
