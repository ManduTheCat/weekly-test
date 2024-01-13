package saturday.sat240113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/*
 * -100,000,000 <= 용액값 <= 100,000,000
 * 같은 양의 두 용액을 혼합하면, 그 특성값은 두 용액의 특성값의 합
 * 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액 만들기
 *
 * 1. 2개 고르기 : 이분탐색)
 * 2. 특성값 구하기
 */
public class BJ_14921_용액합성하기 {
    public static void main(String[] args) throws IOException {
//        String input = "5\n" +
//                "-101 -3 -1 5 93"; // 2
//        BufferedReader br = new BufferedReader(new StringReader(input));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] arr = br.readLine().split(" ");
        int[] vals = new int[N];
        for(int i = 0; i < N; i++) {
            vals[i] = Integer.parseInt(arr[i]);
        }

        int left = 0, right = N - 1;
        int ans = 2 * (int)1e9;

        while(left < right && ans != 0) {
            ans = compare(ans, vals[left] + vals[right]);

//            System.out.printf("%d : %d | ans = %d\n", vals[left], vals[right],ans);

            if(Math.abs(vals[left]) > Math.abs(vals[right])) {
                left++;
            } else {
                right--;
            }

        }

        System.out.println(ans);
    }

    public static int compare(int a, int b) {
        int valA = Math.abs(a);
        int valB = Math.abs(b);

        if(valA > valB) {
            return b;
        } else {
            return a;
        }
    }
}
