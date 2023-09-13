// 한 번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소를 하고 다음 손님들이 사용 가능
// 이때 필요한 최소 객실의 수를 반환하라

import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] reservation = new int[book_time.length][2];
        System.out.println(book_time.length);
        // 문자열로 제공되는 시간을 정수형 분으로 변경
        for(int i = 0; i < book_time.length; i++) {
            StringTokenizer st = new StringTokenizer(book_time[i][0], ":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            
            int start = (hour * 60) + min;
            reservation[i][0] = start;
           
            st = new StringTokenizer(book_time[i][1], ":");
            hour = Integer.parseInt(st.nextToken());
            min = Integer.parseInt(st.nextToken());
            // 청소시간 10분을 추가
            int end = (hour * 60) + min + 10;
            reservation[i][1] = end;
        }
        // 대실 시작 시간을 기준으로 정렬
        Arrays.sort(reservation, (a, b) -> a[0] - b[0]);
        
        List<int[]> room = new ArrayList<>();
        
        for(int i = 0; i < reservation.length; i++) {
            boolean flag = true;
            for(int[] arr : room) {
                // 이미 퇴실이 끝난 방에 입실
                if(arr[1] <= reservation[i][0]) {
                    arr[0] = reservation[i][0];
                    arr[1] = reservation[i][1];
                    flag = false;
                    break;
                }
            }
            // 현재 있는 방이 모두 퇴실 시간이 안지났다면 방 추가
            if(flag) {
                room.add(reservation[i]);
            }
        }
        
        return room.size();
    }
}
