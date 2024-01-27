package saturday.sat230318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2차원 세계의 블록
 * 블록 사이에 고인 빗물의 총량
 * 비는 충분히 많이 온다.
 */
public class BJ_14719_빗물 {
    public static void main(String[] args) throws IOException {
//        String src = "4 4\n" +
//                "3 0 1 4";
//
//        BufferedReader br = new BufferedReader(new StringReader(src));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken()); // 세로 길이
        int W = Integer.parseInt(st.nextToken()); // 가로 길이

        int[] heights = new int[W];
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while(st.hasMoreTokens()) heights[idx++] = Integer.parseInt(st.nextToken());

        int ans = 0;
        // 양쪽 벽 높이를 비교하여 현재 벽에 어느 높이까지 빗물이 고일 수 있나 구하는 방식
        for(int i = 1; i < W - 1; i++) { // 현재 인덱스. 시작과 끝은 제외
            // 현재 벽보다 낮으면 빗물이 고이지 않음
            int left = heights[i];
            int right = heights[i];

            for (int j = 0; j < i; j++) left = Math.max(left, heights[j]);
            for (int j = W - 1; j > i; j--) right = Math.max(right, heights[j]);

            ans += Math.min(left, right) - heights[i];
        }

        System.out.println(ans);
    }
}
