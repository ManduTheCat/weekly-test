package saturday.sat230401;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

/*
 * N개의 스위치, N개의 전구
 * 각 전구는 켜저 있거나 꺼져 있음
 * i번 스위치를 누르면 i-1, i, i+1 세 개의 전구 상태가 변경됨
 * N개 전구들의 현재 상태와 만들고자 하는 상태가 주어졌을 때, 그 상태를 만들기 위해 스위치를 최소 몇 번 누르면 되는지 알아내기 ~
 * 불가능한 경우 -1 출력
 *
 * i-1, i, i+1 -> i, i+1, i+2 : 이렇게 하면 단방향 변경이라 i번 스위치를 눌렀을 때, 이전 전구를 상관안해도 됨
 * 오른쪽 케이스로 변경하면 2 ~ N - 1번 버튼을 누를 수 있음
 * 이러면 첫 번째 스위치 누르는 경우를 따로 해줘야 함
 */
public class BJ_2138_전구와스위치 {
    static final int INF = (int)1e9;
    public static void main(String[] args) throws Exception {
//        String str = "3\n" +
//                "000\n" +
//                "010"; // 3
//        BufferedReader br = new BufferedReader(new StringReader(str));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[] arr = br.readLine().toCharArray(); // 최초 상태
        char[] dest = br.readLine().toCharArray(); // 목표 상태

        char[] copy = arr.clone(); // 1번 스위치 누른 상태
        copy[0] = change(copy[0]);
        copy[1] = change(copy[1]);

        int ans = INF;
        ans = Math.min(ans, func(N, arr, dest));
        ans = Math.min(ans, func(N, copy, dest) + 1);

        if(ans == INF) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    public static int func(int N, char[] arr, char[] dest) {
        int cnt = 0;

        for(int i = 0; i < N - 1; i++) { // 2번부터 N-2번 스위치를 누를 때 변경되는 범위
            if(arr[i] != dest[i]) {
                cnt++; // i번 스위치 누름

                arr[i] = change(arr[i]);
                arr[i+1] = change(arr[i+1]);

                if(i != N - 2) { // 범위 벗어나지 않도록 처리
                    arr[i+2] = change(arr[i+2]);
                }
            }
        }

        // 모든 과정을 거친 후 마지막 글자가 같지 않으면 불가능한 경우
        // 마지막 글자를 바꾸는 방법 없음(N+1번 스위치를 눌러야 하기 때문에 ...)
        if(arr[N-1] != dest[N-1]) {
            return INF;
        }

        return cnt;
    }

    public static char change(char c) {
        return c == '0' ? '1' : '0';
    }
}
