import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_20125_쿠키의신체측정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[][] a = new char[n][n];

        for (int i = 0; i < n; i++) {
            a[i] = br.readLine().toCharArray();
        }

        int[] head = new int[2];
        head[0] = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == '*') {
                    head[0] = i;
                    head[1] = j;
                    break;
                }
            }
            if (head[0] != -1) break;
        }

        int[] ans = new int[5]; // 왼쪽 팔, 오른쪽 팔, 허리, 왼쪽 다리, 오른쪽 다리의 길이

        // 팔 탐색
        for (int i = 0; i < n; i++) {
            if (a[head[0] + 1][i] == '*') {
                if (i < head[1]) ++ans[0];
                else if (i > head[1]) ++ans[1];
            }
        }

        // 허리 탐색
        int leg = -1;
        for (int i = head[0] + 2; i < n; i++) {
            if (a[i][head[1]] != '*') {
                leg = i;
                break;
            }
            ++ans[2];
        }

        // 왼쪽 다리 탐색
        for (int i = leg; i < n; i++) {
            if (a[i][head[1] - 1] != '*') break;
            ++ans[3];
        }

        // 오른쪽 다리 탐색
        for (int i = leg; i < n; i++) {
            if (a[i][head[1] + 1] != '*') break;
            ++ans[4];
        }

        System.out.println((head[0] + 2) + " " + (head[1] + 1));
        for (int i = 0; i < 5; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }

}
