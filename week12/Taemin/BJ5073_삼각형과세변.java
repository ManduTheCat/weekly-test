import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ5073_삼각형과세변 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            // 종료 조건
            if (x == 0 && y == 0 && z == 0) break;

            // Invalid 조건
            if (x >= y && x >= z) {
                if (x >= y + z) {
                    sb.append("Invalid\n");
                    continue;
                }
            } else if (y >= x && y >= z) {
                if (y >= x + z) {
                    sb.append("Invalid\n");
                    continue;
                }
            } else if (z >= x && z >= y) {
                if (z >= x + y) {
                    sb.append("Invalid\n");
                    continue;
                }
            }

            // 삼격형 구분
            int count = 0;
            if (x == y) count++;
            if (x == z) count++;
            if (y == z) count++;

            if (count == 0) sb.append("Scalene\n");
            else if (count == 1) sb.append("Isosceles\n");
            else if (count == 3) sb.append("Equilateral\n");
        }

        System.out.println(sb.toString());
    }
}
