import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ17615_볼모으기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] balls = br.readLine().toCharArray();

        int min = Integer.MAX_VALUE;
        int pointer = -1, move = 0;

        // 1. 빨강을 오른쪽으로 모으기
        move = 0;
        for (int i = balls.length -1; i >= 0; i--) {
            if (pointer == -1 && balls[i] == 'B') {
                pointer = i;
                continue;
            }

            if (pointer != -1 && balls[i] == 'R') {
                pointer--;
                move++;
            }
        }

        min = Math.min(min, move);
        move = 0;
        pointer = -1;

        // 2. 빨강을 왼쪽으로 모으기
        for (int i = 0; i <= balls.length -1; i++) {
            if (pointer == -1 && balls[i] == 'B') {
                pointer = i;
                continue;
            }

            if (pointer != -1 && balls[i] == 'R') {
                pointer++;
                move++;
            }
        }

        min = Math.min(min, move);
        move = 0;
        pointer = -1;

        // 3. 파랑을 오른쪽으로 모으기
        for (int i = balls.length -1; i >= 0; i--) {
            if (pointer == -1 && balls[i] == 'R') {
                pointer = i;
                continue;
            }

            if (pointer != -1 && balls[i] == 'B') {
                pointer--;
                move++;
            }
        }

        min = Math.min(min, move);
        move = 0;
        pointer = -1;

        // 4. 파랑을 왼쪽으로 모으기
        for (int i = 0; i <= balls.length -1; i++) {
            if (pointer == -1 && balls[i] == 'R') {
                pointer = i;
                continue;
            }

            if (pointer != -1 && balls[i] == 'B') {
                pointer++;
                move++;
            }
        }

        min = Math.min(min, move);

        System.out.println(min);
    }
}
