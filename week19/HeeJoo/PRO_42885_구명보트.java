package saturday.sat230325;

import java.util.Arrays;

/*
 * 최대 2명, 무게 제한 있음
 * 구명보트를 최대한 적게 사용하여 모든 사람 구출
 */
public class PRO_42885_구명보트 {
    public static void main(String[] args) {
        int[] people = new int[]{70,50,80,50};
        int limit = 100;

        // 3
        System.out.println(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;

        while(right > left) {
            if(people[left] + people[right] > limit) {
                answer++;
                right--;
            } else if(people[left] + people[right] <= limit) {
                answer++;
                left++;
                right--;
            }
            if(left == right) {
                answer++;
            }
        }

        return answer;
    }
}
