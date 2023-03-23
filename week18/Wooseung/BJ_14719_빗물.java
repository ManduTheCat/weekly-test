package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// H 높이, W 너비 
// 한 칸의 용량은 1
// 빗물이 전혀 고이지 않을 경우 0출력 아닐 시 빗물의 총량 출력

// 첫번째 열과 마지막열은 비가 고일 수 없으니 고려하지 않는다.
// 현재 열을 중심으로 본인의 높이보다 큰 수중 가장 큰 높이를 left, right에 저장
// left, right 중 작은 수와 본인의 높이를 뺀 값은 현재 열에 고일 수 있는 빗물의 높이가 나온다.
public class BJ_14719_빗물 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int ans = 0;

        int[] arr = new int[W];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        //
       for(int i = 1; i < W - 1; i++) {
            // 현재 본인의 높이로 left, right 초기화
            int left = arr[i];
            int right = arr[i];

            // 현재 열을 기준으로 왼쪽에서 가장 큰 높이를 구한다.
            for(int j = 0; j < i; j++) {
                left = Math.max(left, arr[j]);
            }
            // 현재 열을 기준으로 오른쪽에서 가장 높은 높이를 구한다.
            for(int j = W - 1; j > i; j--) {
                right = Math.max(right, arr[j]);
            }
            // left, right중 작은 값에서 본인의 높이를 빼면 빗물이 고일 수 있는 높이를 구할 수 있다.
            ans += Math.min(left, right) - arr[i];

       }
        System.out.println(ans);
    }

}
