package saturday.sat230325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 많은 종류의 산성 용액과 알칼리성 용액
 * 각 용액에는 해당 용액의 특성을 나타내는 하나의 정수가 주어짐
 * 산성 용액 : 1 ~ 1,000,000,000까지의 양의 정수
 * 알칼리성 용액 : -1 ~ -1,000,000,000까지의 음의 정수
 * 혼합 용액의 특성 = 각 용액의 특성값의 합
 * 산성 용액과 알칼리성 용액의 특성값이 "정렬"된 순서로 주어졌을 때, 이 중 두 개의 서로 다른 용액을 혼합하여
 * 특성값이 "0"에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램
 */
public class BJ_2467_용액 {
    public static void main(String[] args) throws IOException {
        String src = "5\n" +
                "-99 -2 -1 4 98";
//        BufferedReader br = new BufferedReader(new StringReader(src));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 전체 용액의 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] array = new int[N];
        for(int i = 0; i < N; i++) array[i] = Integer.parseInt(st.nextToken());

//        -99 98
        int[] ans = solution(N, array);
        for (int an : ans) {
            System.out.print(an + " ");
        }
    }

    public static int[] solution(int N, int[] array) {
        int[] answer = new int[2];

        int left = 0, right = array.length - 1;
        int interval = (int)1e9 * 2; // 주의 !! 1000000000 1000000000이 들어오면 2000000000이 돼서 *2 해줘야 함
        while(left < right) {
            int tmp = array[left] + array[right];
            if(Math.abs(tmp) < interval) {
                interval = Math.abs(tmp);
                answer[0] = array[left];
                answer[1] = array[right];
            }

            if(tmp < 0) left++;
            else right--;
        }

        return answer;
    }
}
