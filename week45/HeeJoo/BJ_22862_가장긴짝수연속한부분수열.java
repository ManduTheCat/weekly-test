package saturday.sat231209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 길이 N인 수열 S : 1 이상인 정수로 이루어져 있음
 * 수열 S에서 원하는 위치에 있는 수를 골라 최대 K번 삭제 가능
 * 수열 S에서 최대 K번의 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이 구하기
 */
public class BJ_22862_가장긴짝수연속한부분수열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열 길이 N
        int K = Integer.parseInt(st.nextToken()); // 최대 삭제 횟수 K

        boolean[] arr = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            if(Integer.parseInt(st.nextToken()) % 2 == 0) {
                arr[i] = true;
            };
        }

        int ans = 0;
        int left = 0; // 부분 수열 시작 지점
        int right = 0; // 부분 수열 현재 지점
        int odd = 0; // 부분 수열의 홀수 개수

        while(right < N) { // 최대 범위
            if(odd < K) { // 홀수 삭제 가능 ~ 연장 가능
                if(!arr[right]) { // 이번 수가 홀수인 경우
                    odd++;
                }
                ans = Math.max(ans, right - left + 1 - odd); // 연속 짝수 길이 갱신
                right++;
            } else if(arr[right]) { // 불가능한데 이번 수가 짝수인 경우 ~ 연장 가능
                ans = Math.max(ans, right - left + 1 - odd); // 연속 짝수 길이 갱신
                right++;
            } else { // 삭제 불가, 홀수 ~ 연장이 불가능한 경우
                if(!arr[left]) { // 제외되는 부분이 홀수인 경우
                    odd--;
                }
                left++; // 제외
            }
        }

        System.out.println(ans);
    }
}
