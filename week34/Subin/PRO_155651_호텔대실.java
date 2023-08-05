import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 퇴실 시간 순으로 정렬
        // 퇴실 시간이 같다면 입실 시간 순으로 정렬
        Arrays.sort(book_time, (b1, b2) -> b1[1].compareTo(b2[1]) == 0 ? b1[0].compareTo(b1[0]) : b1[1].compareTo(b2[1]));
        
        // room 입실 가능 정보
        LinkedList<String> room = new LinkedList();
        
        for (String[] b: book_time) {
            if (room.isEmpty() || b[0].compareTo(room.get(0)) < 0) { // 입실이 불가능하다면 방을 추가한다.
                // 방은 퇴실 시간 + 청소 시간 10분 이후부터 사용 가능하다.
                room.add(addTenMinute(b[1]));
            } else { // 입실 가능한 방이 있다면, 가능한 방 중 가장 퇴실 시간이 느린 방에 들어간다.
                int idx = 0;
                while (idx < room.size() && b[0].compareTo(room.get(idx)) >= 0) idx++;
                // 방은 퇴실 시간 + 청소 시간 10분 이후부터 사용 가능하다.
                room.set(idx - 1, addTenMinute(b[1]));
            }
            Collections.sort(room);
        }
        return room.size();
    }
    
    // 문자열 시간에 10분 더하는 함수
    private String addTenMinute(String time) {
        char[] newtime = time.toCharArray();
        
        if (newtime[3] == '5') {
            newtime[3] = '0';
            if (newtime[1] == '9') {
                newtime[1] = '0';
                newtime[0] += 1;
            } else {
                newtime[1] += 1;
            }
        } else {
            newtime[3] += 1;
        }
        
        return String.valueOf(newtime);
    }
}
