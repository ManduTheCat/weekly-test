package saturday.sat240113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

/*
 * 팀 하나당 두 명의 학생으로 구성
 * 각 학생들의 코딩 역량은 모두 다르며, 한 팀의 팀원이여야 함
 * 공정성을 높이기 위해 팀원 코딩 역량의 합을 최대한 일정하게 유지
 * 최소 팀 코딩 역량 구하기
 *
 * 1. 정렬
 * 2. 오름차순 + 내림차순
 */
public class BJ_20044_ProjectTeams {
    public static void main(String[] args) throws IOException {
//        String input = "2\n" +
//                "1 7 5 8"; // 9
//        BufferedReader br = new BufferedReader(new StringReader(input));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 팀 수

        String[] arr = br.readLine().split(" ");
        int[] abilities = new int[2 * n];
        for(int i = 0; i < 2 * n; i++) {
            abilities[i] = Integer.parseInt(arr[i]);
        }

        Arrays.sort(abilities);
        int ans = (int)1e9;
        for(int i = 0; i < n; i++) {
            ans = Math.min(ans, abilities[i] + abilities[2 * n - 1 - i]);
        }

        System.out.println(ans);
    }
}
