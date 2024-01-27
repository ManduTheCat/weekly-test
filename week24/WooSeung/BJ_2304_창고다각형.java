import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st;

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        int ans = 0;
        int curIdx = 0;
        int maxLeft = 0;
        int maxRight;

        for(int i = 0; i < N; i++) {

            if(arr[i][1] >= maxLeft) {
                ans += arr[i][1] + maxLeft * (arr[i][0] - curIdx - 1);
                curIdx = arr[i][0];
                maxLeft = arr[i][1];
            }else{
                maxRight = arr[i][1];
                for(int j= i+1;j<N;j++){
                    maxRight = Math.max(maxRight, arr[j][1]);
                }
                int temp = Math.min(maxRight, maxLeft);
                ans += (arr[i][0]-curIdx) * temp;
                curIdx = arr[i][0];
            }

        }

        System.out.println(ans);

    }
}
