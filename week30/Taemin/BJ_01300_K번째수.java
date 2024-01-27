import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long left = 1;
        long right = (long)n * n;

        /* lower-bound */
        while (left < right) {
            long mid = (left + right) / 2;
            long count = cal(n, mid);

            if (k <= count) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.print(left);
    }

    private static long cal(int n, long mid) {
        long result = 0;
        for (int i = 1; i <= n; i++) {
            result += Math.min((mid / i), n);
        }

        return result;
    }
}
