package saturday.sat240113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

/*
 * N개의 눈덩이
 * 각 눈덩이의 지름은 Hi
 * 하나의 눈사람은 두 개의 눈덩이로 구성되며, 눈덩이 하나를 아래에 두고 그 눈덩이보다 크지 않은 다른 눈덩이를 쌓아올리는 방식
 * 눈사람의 키는 두 눈덩이 지름의 합
 * N개 중 서로 다른 4개를 골라서 눈사람을 총 2개 만들 때, 두 눈사람의 키 차이 최솟값 구하기
 *
 * 1. 2개 골라서 엘사 눈사람 만들기
 * 2. 투포인터로 안나 눈사람 만들기
 */
public class BJ_20366_같이눈사람만들래 {
    public static void main(String[] args) throws IOException  {
        String input = "5\n" +
                "3 5 2 5 9";
        BufferedReader br = new BufferedReader(new StringReader(input));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 눈덩이 수
        int ans = (int)1e9;

        int[] balls = new int[N];
        String[] arr = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            balls[i] = Integer.parseInt(arr[i]);
        }

        Arrays.sort(balls); // 2 3 5 5 9

        // 1. 이중 for문을 이용한 엘사 눈사람 만들기
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                int elsa = balls[i] + balls[j]; // 엘사의 눈사람 높이

                // 2. 투포인터로 안나 눈사람 만들기
                int left = 0, right = N - 1;
                while(left < right) {
                    // 눈덩이가 겹치는 경우 continue
                    if(left == i || left == j) {
                        left++;
                        continue;
                    }
                    else if(right == i || right == j) {
                        right--;
                        continue;
                    }

                    int anna = balls[left] + balls[right];
                    ans = Math.min(ans, Math.abs(elsa - anna));

                    if(elsa > anna) {
                        left++;
                    } else if(elsa < anna){
                        right--;
                    } else { // elsa == anna : 높이 차이가 0보다 작을 수 없음
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
