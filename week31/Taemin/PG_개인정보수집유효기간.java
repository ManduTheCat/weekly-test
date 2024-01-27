import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 결과를 담을 리스트
        ArrayList<Integer> result = new ArrayList<>();
        
        // 약관 정보를 담을 해시맵
        HashMap<String, Integer> map = new HashMap<>();
        
        // 현재 날짜를 일 수로 환산
        long todayTotal = calDays(today);
        
        // 약관 정보 갱신
        for (int i = 0; i < terms.length; i++) {
            String term = terms[i].split(" ")[0];
            int duration = Integer.parseInt(terms[i].split(" ")[1]);
            
            map.put(term, duration);
        }
        
        // 개인정보 체크
        for (int i = 0; i < privacies.length; i++) {
            long created = calDays(privacies[i].split(" ")[0]);
            created += map.get(privacies[i].split(" ")[1]) * 28;
            
            // 현재 날짜가 유효 기간을 넘은 경우 저장
            if (todayTotal >= created) result.add(i + 1);
        }
        
        // 정수형 리스트를 정수형 배열로 변환
        int[] answer = result.stream().mapToInt(i->i).toArray();
        return answer;
    }
    
    // 날짜 정보를 이용해서 일 수로 변환
    public long calDays(String date) {
        int year = Integer.parseInt(date.split("\\.")[0]);
        int month = Integer.parseInt(date.split("\\.")[1]);
        int day = Integer.parseInt(date.split("\\.")[2]);
        long result = year * 12 * 28 + month * 28 + day;
        
        return result;
    }
}
