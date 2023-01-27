package saturday.sat230121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
육각형으로 이루어진 벌집
중앙의 방 1부터 이웃하는 방에 돌아가면서 1씩 증가하는 번호
숫자 N이 주어졌을 때, 벌집의 중앙 1에서 N번 방까지의 최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나가는지를 계산하는 프로그램
시작과 끝 방을 포함한다.
 */
public class BJ_2292_벌집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 숫자 N

        // N번 방이 몇 번째 껍질에 위치하는지
        // 1 / 2-7 : 6 / 8-19 : 12 / 20-37 : 18 / 38 ~61 : 24 ~ 6개씩 증가 ..
        N -= 1; // 중앙
        int layer = 1; // 몇 번째 껍질인지

        while(N > 0){
            N -= 6 * layer; // 6, 12, 18...
            layer++;
        }
        System.out.println(layer);
    }
}
