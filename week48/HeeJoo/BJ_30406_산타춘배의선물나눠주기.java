package saturday.sat240106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N개의 선물 -> N/2마리 아기냥이에게 2개씩 나누어주기
 * N개의 상품은 각각 가격 존재 ... 0 이상 3 이하
 * 선물 만족도 = 받은 선물 2개의 가격을 XOR한 값
 * 선물을 나눠줬을 때 얻을 수 있는 만족도의 최댓값 구하기
 *
 * (0, 3), (1, 2) = 3 / (0, 2), (1, 3) = 2 / (0, 1), (2, 3) = 1
 */
public class BJ_30406_산타춘배의선물나눠주기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        int[] arr = new int[4];
        for(String str : inputs) {
            arr[Integer.parseInt(str)]++;
        }

        int[][] vals = new int[][]{{0, 3, 3}, {1, 2, 3}, {0, 2, 2}, {1, 3, 2}, {0, 1, 1}, {2, 3, 1}};

        int ans = 0;
        for(int[] val : vals) {
            if(arr[val[0]] == 0 || arr[val[1]] == 0) {
                continue;
            }

            if(arr[val[0]] <= arr[val[1]]) {
                ans += val[2] * arr[val[0]];
                arr[val[1]] -= arr[val[0]];
                arr[val[0]] = 0;
            } else {
                ans += val[2] * arr[val[1]];
                arr[val[0]] -= arr[val[1]];
                arr[val[1]] = 0;
            }
        }

        System.out.println(ans);
    }
}
