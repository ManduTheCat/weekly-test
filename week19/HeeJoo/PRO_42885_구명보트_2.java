package saturday.sat230325;

import java.util.Arrays;

/*
 * 최대 2명 -> 1명씩 태우는 경우의 수 - 두 명을 태우는 경우의 수
 */
public class PRO_42885_구명보트_2 {
    public static void main(String[] args) {
        int[] people = new int[]{70,50,80,50};
        int limit = 100;

        // 3
        System.out.println(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0, right = people.length - 1;
        while(left < right) {
            if(people[left] + people[right--] <= limit) left++;
        }

        return people.length - left;
    }
}

