import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        
        // 분 단위로 바꿔서 저장
        ArrayList<int[]> times = new ArrayList<>();
        for (int i = 0; i < book_time.length; i++) {
            int start = Integer.parseInt(book_time[i][0].split(":")[0]) * 60 + 
                        Integer.parseInt(book_time[i][0].split(":")[1]);
            int end = Integer.parseInt(book_time[i][1].split(":")[0]) * 60 +
                      Integer.parseInt(book_time[i][1].split(":")[1]);
            
            times.add(new int[]{start, end + 10});
        }
        
        // 시작 시점을 기준으로 오름차순 정렬
        Collections.sort(times, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                
                return Integer.compare(o1[0], o2[0]);
            }
        });
        
        // // 정렬된 상태 (디버그용)
        // for (int[] time : times) {
        //     System.out.println(time[0] + " " + time[1]);
        // }
        // System.out.println();
        
        
                
        // 필요한 방의 수 확인
        ArrayList<Integer> rooms = new ArrayList<>();
        rooms.add(times.get(0)[1]);
        
        // (디버그용)
        // System.out.println(rooms.get(0));
        
        for (int i = 1; i < times.size(); i++) {
            // 오름차순 정렬
            Collections.sort(rooms);
            
            int start = times.get(i)[0];
            int end = times.get(i)[1];
            
            // 입실 시간이 정리된 시간보다 크거나 같은 경우 해당 방의 종료 시간 갱신
            if (rooms.get(0) <= start) {
                rooms.set(0, end);
            }
            // 입실 시간이 가장 빨리 정리된 방의 시간 보다 빠른 경우 방을 추가
            else {
                rooms.add(end);
            }
            
            // // (디버그용)
            // for (Integer room : rooms) {
            //     System.out.print(room + " ");
            // }
            // System.out.println();
        }
        
        // 사용한 방의 수 리턴
        return rooms.size();
    }
}
