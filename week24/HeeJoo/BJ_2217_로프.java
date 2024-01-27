package saturday.sat230521;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/*
 * N개의 로프 존재
 * 각각의 로프는 들 수 있는 물체의 중량이 서로 다를 수도 ~ 있음
 * 여러 개의 로프를 병렬로 연결하면 각각 로프에 걸리는 중량을 나눌 수 있음
 * k개의 로프를 사용하여 중량이 w인 물체를 들어올릴 때, 각 로프는 모두 고르게 w/k만큼 중량을 듦
 * 각 로프들에 대한 정보가 주어졌을 때, 들어올릴 수 있는 물체의 최대 중량을 구하는 프로그램
 * 모든 로프를 사용할 필요는 없다.
 * 1 <= N <= 100,000
 */
public class BJ_2217_로프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int w = 0, count = 1;
        Arrays.sort(arr, Collections.reverseOrder());
        for (int i : arr) {
            w = Math.max(w, i * count++);
        }

        System.out.println(w);
    }
}

