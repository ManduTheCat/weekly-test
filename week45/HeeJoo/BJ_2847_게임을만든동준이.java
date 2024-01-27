package saturday.sat231209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * N개의 레벨. 각 레벨을 클리어할 때 마다 점수 획득
 * 레벨 난이도 순 배치했으나, 실수로 어려운 에벨보다 점수를 많이 받는 경우 존재
 * 점수는 항상 양수이어야 하고, 1만큼 감소시키는 것이 1번
 * 레벨별 점수가 주어졌을 때, 몇 번을 감소시키면 되는지 구하는 프로그램
 */
public class BJ_2847_게임을만든동준이 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 레벨의 수 N

        int[] level = new int[N];
        for(int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        for(int i = N - 1; i > 0; i--) {
            if(level[i] <= level[i-1]) { // 현재 레벨이 이전 레벨보다 낮거나 같은 경우
                ans += level[i-1] - level[i] + 1;
                level[i-1] = level[i] - 1;
            }
        }

        System.out.println(ans);
    }
}
