package saturday.sat230521;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * N개의 막대 기둥 일렬 ...
 * 폭은 1m, 높이는 다를 수 있음 ...
 * 창고에는 모든 기둥이 들어간다
 *
 * 1. 지붕은 수평 부분과 수직 부분으로 구성되며, 모두 연결
 * 2. 지붕의 수평 부분은 반드시 어떤 기둥의 윗면과 닿아야 함
 * 3. 지붕의 수직 부분은 반드시 어떤 기둥의 옆면과 닿아야 함
 * 4. 지붕의 가장 자리는 땅에 닿아야 함
 * 5. 비가 올 때 물이 고이지 않도록, 지붕의 어떤 부분도 오목하지 않아야 함
 *
 * 면적이 가장 작은 창고 만들기
 */
public class BJ_2304_창고다각형 {
    public static void main(String[] args) throws IOException {
        String src = "7\n" +
                "2 4\n" +
                "11 4\n" +
                "15 8\n" +
                "4 6\n" +
                "5 3\n" +
                "8 10\n" +
                "13 6";

        BufferedReader br = new BufferedReader(new StringReader(src));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 기둥의 개수 N. 1 이상 1,000 이하

        // x좌표, y좌표
        StringTokenizer st = null;
        int[][] arr = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, ((o1, o2) -> o1[0] - o2[0]));
        int idx = 0; // 가장 높은 기둥의 idx
        for(int i = 0; i < N; i++) {
            if(arr[i][1] > arr[idx][1]) {
                idx = i;
            }
        }

        int ans = 0;
        Stack<int[]> stack = new Stack<>();

        // 우상향
        stack.push(arr[0]);
        for (int i = 1; i < idx; i++) {
            if(arr[i][1] >= stack.peek()[1]) { // 이전 기둥보다 현재 기둥이 더 높거나 같을 때
                ans += stack.peek()[1] * (arr[i][0] - stack.peek()[0]);
                stack.push(arr[i]);
            }
        }

        ans += stack.peek()[1] * (arr[idx][0] - stack.peek()[0]);
        stack.clear();

        // 좌상향
        stack.push(arr[N - 1]);
        for (int i = N - 2; i > idx; i--) {
            if(arr[i][1] >= stack.peek()[1]) { // 이전 기둥보다 현재 기둥이 더 높거나 같을 때
                ans += stack.peek()[1] * (stack.peek()[0] - arr[i][0]);
                stack.push(arr[i]);
            }
        }

        ans += stack.peek()[1] * (stack.peek()[0] - arr[idx][0]);

        ans += arr[idx][1]; // top 기둥면적 추가
        System.out.println(ans);

    }
}

