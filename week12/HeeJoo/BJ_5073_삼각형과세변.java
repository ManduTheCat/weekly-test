package saturday.sat230121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
Equilateral : 세 변의 길이가 모두 같은 경우
Isosceles : 두 변의 길이만 같은 경우
Scalene : 세 변의 길이가 모두 다른 경우
단, 주어진 세 변의 길이가 삼각형의 조건을 만족하지 못하는 경우에는 "Invalid" 출력
삼각형의 조건 : 가장 긴 변의 길이보다 나머지 두 변의 길이의 합이 길어야 함
세 변의 길이가 주어질 때 위 정의에 따른 결과 출력
각 줄에는 1,000을 넘지 않는 양의 정수 3개 입력
마지막 줄은 "0 0 0"이며 계산x
 */
public class BJ_5073_삼각형과세변 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while(true){
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()) pq.add(Integer.parseInt(st.nextToken()));

            int x = pq.poll(), y = pq.poll(), z = pq.poll(); // z가 가장 긴 변의 길이

            // 0이 입력되면 마지막 줄 ~ 종료
            if(x == 0) break;

            // 삼각형의 조건 불만족
            if(x + y <= z) sb.append("Invalid\n");
            else{
                // 세 변의 길이가 모두 같은 경우
                if(x == y && x == z) sb.append("Equilateral\n");
                // 두 변의 길이만 같은 경우
                else if((x == y && y != z) || (x != y && y == z)) sb.append("Isosceles\n");
                else sb.append("Scalene\n");
            }
        }
        System.out.println(sb);

    }

}
