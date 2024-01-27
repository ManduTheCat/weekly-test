package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_02629_양팔저울 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfW = Integer.parseInt(br.readLine());
        int[] w = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        int numOfM = Integer.parseInt(br.readLine());
        int[] m = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

        boolean[] check = new boolean[40_001];
        check[0] = true;

        for (int i = 0; i < numOfW; i++) {
            int weight = w[i];

            for (int j = 40_000; j >= 0; j--) {
                if (check[j]) check[j + weight] = true;
            }

            for (int j = 0; j <= 40_000; j++) {
                if (check[j]) check[Math.abs(j - weight)] = true;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numOfM; i++) {
            String result = (check[m[i]]) ? "Y" : "N";
            builder.append(result).append(" ");
        }

        System.out.println(builder.toString());
    }
}
