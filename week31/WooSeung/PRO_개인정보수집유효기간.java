// 약관마다 개인정보 보관 유효기간이 정해져 있다.
// 유효기간 전까지만 보관 가능, 지났다면 반드시 파기
// ex) 유효기간 12 달 2021년 1월 5일에 수집 2022년 1월 4일까지 보관 가능 2022년 1월 5일이면 파기해야한다.
// 모든 달은 28일 까지 있다.
// 개인정보의 날짜는 today 이전의 날짜만 주어진다.

import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
    
        List<Integer> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();

        // 약관 저장
        for (int i = 0; i < terms.length; i++) {
            map.put(terms[i].split(" ")[0], terms[i].split(" ")[1]);
        }

        int year = Integer.parseInt(today.split("\\.")[0]);
        int month = Integer.parseInt(today.split("\\.")[1]);
        int day = Integer.parseInt(today.split("\\.")[2]);

        // 오늘 날짜에서 개인 정보의 날짜를 차감
        for (int i = 0; i < privacies.length; i++) {
            String date = privacies[i].split(" ")[0];
            int type = Integer.parseInt(map.get(privacies[i].split(" ")[1]))*28;

            int num = (year - Integer.parseInt(date.split("\\.")[0]))*28*12
                    + (month - Integer.parseInt(date.split("\\.")[1]))*28
                    + (day - Integer.parseInt(date.split("\\.")[2]));

            if (num >= type) {
                list.add(i+1);
            }
        }
        
        int[] answer = new int[list.size()];
        
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
