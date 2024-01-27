package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_02847_게임을만든동준이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        int total = 0;
        for (int i = n-2; i >= 0; i--) {
            int compare = array[i+1];
            if (array[i] >= compare) {
                total += (array[i] - compare + 1);
                array[i] = compare - 1;
            }
        }

        System.out.println(total);
    }
}
