package baekjoon;

import java.io.*;

import java.util.List;
import java.util.StringTokenizer;

public class BJ_19637_IF문좀대신써줘 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] type = new String[N];
        int[] power = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            type[i] = st.nextToken();
            power[i] = Integer.parseInt(st.nextToken());
        }
        // 이진탐색
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());
            int start = 0, end = N - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (power[mid] < input) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            bw.write(type[start] + "\n");
        }
        bw.flush();
    }

}
