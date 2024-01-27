/*
 * 문자열 x : 0과 1로만이루어짐
 * x에 있는 "110"을 뽑아서, 임의의 위치에 다시 삽입하는 행동을 통해 x를 최대한 사전 순으로 앞에 오도록 만들기
 * 변형해서 만들 수 있는 문자열 중 사전 순으로 가장 앞에 오는 문자열 구하기
 
 * 110이 이동해서 사전 순으로 더 앞에 오는 문자열이 되려면 ... 111을 110으로 대체하는 경우만 가능
 * 1. 앞에서부터 1의 개수 세기
 * 2. 0이 나오면 1의 개수에 따라 처리
 * 2-1. 지금까지 1의 개수 < 2 : 111이 안되는 경우 ... 문자열 순서 유지
 * 2-2. 지금까지 1의 개수 >= 2 : 111을 110으로 대체가능한 경우 ... 110 개수++ // 이 때, 1의 개수 - 2 처리 ... 110에 2개 들어감
 * 3. 탐색 종료 후 지금까지 문자열 + 110의 개수 + 남은 1의 개수 
 */

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < s.length; t++) {
            int one = 0; // 지금까지 1의 개수
            int ooz = 0; // 만들 수 있는 110의 개수
            
            for(int i = 0; i < s[t].length(); i++) {
                if(s[t].charAt(i) == '1') { // 1인 경우
                    one++;
                } else { // 0인 경우
                    if(one < 2) { // 110 만들기 불가능
                        while(one > 0) {
                            sb.append("1");
                            one--;
                        }
                        
                        sb.append("0");
                    } else { // 110 만들기 가능 ~ 가장 앞의 11에 0붙이기
                        ooz++;
                        one -= 2;
                    }
                }
            }
            
            while(ooz-- > 0) {
                sb.append("110");
            }
            
            while(one-- > 0) {
                sb.append("1");
            }
            
            answer[t] = sb.toString();
            sb.setLength(0);
        }
        
        return answer;
    }
}
