import java.util.*;

/*
1. 시간을 분으로 변환
2. 시작 시간 순으로 정렬 후 차례대로 삽입
    a. 빈 방이 없으면 방을 하나 추가하고 삽입
    b. 비어있는 방이 있으면 그대로 넣기
*/
class Solution {
    public int solution(String[][] book_time) {
      //init
        Reservation[] reservations = new Reservation[book_time.length];
        int start;
        int end;
        StringTokenizer st;
      //시간문자열 파싱. 분단위로 저장
        for(int i=0;i<book_time.length;i++){
            st = new StringTokenizer(book_time[i][0],":");
            start = Integer.parseInt(st.nextToken())*60 
                + Integer.parseInt(st.nextToken());
            st = new StringTokenizer(book_time[i][1],":");
            end = Integer.parseInt(st.nextToken())*60 
                + Integer.parseInt(st.nextToken());
            reservations[i] = new Reservation(start, end); 
        }
        
        //시작 시간 순으로 정렬
        Arrays.sort(reservations, (a, b) -> a.start - b.start);
        
        ArrayList<Reservation> list = new ArrayList<>();
        for(int i=0;i<reservations.length;i++){
            boolean isNewRoom = true;
            for(Reservation r : list){
                if(r.end+10 <= reservations[i].start){
                    r.start = reservations[i].start;
                    r.end = reservations[i].end;
                    isNewRoom = false;
                    break;
                }
            }
            if(isNewRoom) list.add(reservations[i]);
        }
        
        int answer = list.size();
        return answer;
    }
}

class Reservation{
    int start;
    int end;
    
    public Reservation(int start, int end){
        this.start = start;
        this.end = end;
    }
}
