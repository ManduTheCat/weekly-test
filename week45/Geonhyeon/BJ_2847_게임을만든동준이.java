import java.io.*;

public class BJ_2847_게임을만든동준이 {
    public static void main(String[] args) throws IOException {
        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int total = 0;
        //logic
        for (int i = N - 2; i >= 0; i--) {
            if (arr[i+1] <= arr[i]) {
                total += arr[i]-arr[i+1]+1;
                arr[i] = arr[i+1]-1;
            }
        }

        bw.write(total+ " ");
        bw.flush();
        bw.close();
        br.close();
    }
}
