package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N이 2 이상 100,000 이하의 정수이기 때문에 완전탐색으로 할 경우 시간초과 발생
// 왼쪽, 오른쪽 끝 인덱스의 값을 더한 값을 비교하면서 0에 가까운 수를 구한다.
public class BJ_2467_용액 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;
        int gap = Integer.MAX_VALUE;
        int sum = 0;
        int ansLeft = 0;
        int ansRight = 0;

        // 왼쪽 인덱스가 오른쪽 인덱스와 값이 같거나 큰 경우는 제외
        while(left < right) {
            // 절대값을 구하기 위해서 현재 두 포인터의 값을 더 한다.
            sum = arr[left] + arr[right];
            // 지금까지 구한 0에 가까운 차이와 같거나 그보다 가깝다면 갱신
            if(Math.abs(sum) <= gap) {
                ansLeft = arr[left];
                ansRight = arr[right];
                gap = Math.abs(sum);
            }
            // 더 한값이 0보다 크다면 오른쪽 인덱스 이동 작다면 왼쪽 인덱스 이동
            if(sum > 0) {
                right--;
            }else {
                left++;
            }

        }

        System.out.println(ansLeft + " " + ansRight);

    }

}
