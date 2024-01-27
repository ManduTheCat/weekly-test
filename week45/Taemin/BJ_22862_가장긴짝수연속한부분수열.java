package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_22862_가장긴짝수연속한부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] array = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt).toArray();

        int longest = 0;
        int[] count = new int[2]; /* 짝수(0), 홀수(1) */
        int start = 0;
        int end = -1;

        while (true) {
            if (end == n-1) break;

            if (count[1] <= k && end < n-1) {
                end++;
                int idx = array[end] % 2;
                count[idx]++;
                longest = Math.max(longest, count[0]);

            } else {
                int idx = array[start] % 2;
                count[idx]--;
                start++;
            }
        }

        System.out.println(longest);
    }
}
