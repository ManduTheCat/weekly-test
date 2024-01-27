import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_20125_쿠키의신체측정 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] ans = new int[5];
        int[] heart = {-1, -1};
        char[][] arr = new char[N][N];

        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        // 심장 위치 찾기
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] == '*') {
                    heart[0] = i + 1;
                    heart[1] = j;
                }
                if(heart[0] != -1 && heart[1] != -1) {
                    break;
                }
            }
            if(heart[0] != -1 && heart[1] != -1) {
                break;
            }
        }
        // 왼쪽 팔 길이 구하기
        int count = 0;
        for(int j = heart[1] - 1; j >= 0; j--) {
            if(arr[heart[0]][j] == '*') {
                count++;
            }
        }
        ans[0] = count;

        // 오른쪽 팔 길이 구하기
        count = 0;
        for(int j = heart[1] + 1; j < N; j++) {
            if(arr[heart[0]][j] == '*') {
                count++;
            }
        }
        ans[1] = count;

        // 허리 길이 구하기
        count = 0;
        for(int i = heart[0] + 1; i < N; i++) {
            if(arr[i][heart[1]] == '*') {
                count++;
            }
        }
        ans[2] = count;

        // 왼쪽 다리 길이 구하기
        count = 0;
        for(int i = heart[0] + 1; i < N; i++) {
            if(arr[i][heart[1] - 1] == '*') {
                count++;
            }
        }
        ans[3] = count;

        // 오른쪽 다리 길이 구하기
        count = 0;
        for(int i = heart[0] + 1; i < N; i++) {
            if(arr[i][heart[1] + 1] == '*') {
                count++;
            }
        }
        ans[4] = count;


        System.out.println((heart[0] + 1) + " " + (heart[1] + 1));
        for(int a : ans) {
            System.out.print(a + " ");
        }



    }

}
