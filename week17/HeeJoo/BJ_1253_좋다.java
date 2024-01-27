package saturday.sat230311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 그 수를 "좋다(GOOD)"고 한다.
N개의 수 중에서 좋은 수가 몇 개인지 구하는 프로그램
! 수의 위치가 다르면 같이 같아도 다른 수
 */
public class BJ_1253_좋다 {
    public static void main(String[] args) throws IOException {
//        String src = "10\n" +
//                "1 2 3 4 5 6 7 8 9 10";
//        BufferedReader br = new BufferedReader(new StringReader(src));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수 N

        // N개의 수
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        Arrays.sort(arr);

        for(int idx = 0; idx < arr.length; idx++) {
            int left = 0;
            int right = arr.length - 1;

            while(left < right) {
                if(left == idx) {
                    left++;
                    continue;
                } else if(right == idx) {
                    right--;
                    continue;
                }

                if(arr[idx] == arr[left] + arr[right]) {
                    ans++;
                    break;
                } else if(arr[idx] < arr[left] + arr[right]) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        System.out.println(ans);

    }
}
