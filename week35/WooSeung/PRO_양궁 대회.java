// 어피치가 화살 n발을 다 쏜후에 라이언이 화살 n발을 쏜다.
// k점에 어피치가 a발을 맞췄다면 라이언은 최소 a + 1발을 맞춰야지 해당 점수를 가질 수 있다.
// 최종적으로 가장 큰 점수차로 어피치를 이기는 경우의 맞힌 과녁 점수를 배열형태로 반환
// 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우 
// 가장 낮은 점수를 더 많이 맞힌 경우를 return 해주세요.
// 만약 라이언이 우승할 수 없는 경우 -1을 반환

class Solution {

    static int gap = -1;
    static int[] answer;

    public int[] solution(int n, int[] info) {
        answer = new int[11];
        dfs(0, n, new int[11], info);
        System.out.println(gap);
        if (gap == -1) {
            return new int[]{-1};
        } else {
            return answer;
        }
    }

    static void dfs(int cnt, int n, int[] score, int[] info) {
        if (cnt == n) {
            int apeach = 0;
            int lion = 0;
            for (int i = 0; i < info.length; i++) {
                // 라이언과 어피치가 모두 못 맞힌 경우는 넘어간다.
                if(info[i] == 0 && score[i] == 0) {
                    continue;
                }
                // 라이언이 어피치보다 하나 이상 더 맞힌 경우에만 라이언이 점수를 획득한다.
                if (info[i] >= score[i]) {
                    apeach += (10 - i);
                } else {
                    lion += (10 - i);
                }
            }
            // 만약 라이언이 얻은 점수가 어피치보다 크다면
            if (lion > apeach) {
                int temp = lion - apeach;
                // 그 점수 차이가 가장 큰 점수 차이라면
                if (gap <= temp) {
                    // 라이언이 맞힌 과녁 점수를 answer배열에 옮겨닮는다.
                    for (int i = 0; i < 11; i++) {
                        answer[i] = score[i];
                    }
                    gap = temp;
                }
            }
            return;
        }
        
        for (int i = 0; i < score.length && score[i] <= info[i]; i++) {
            score[i]++;
            dfs(cnt + 1, n, score, info);
            score[i]--;
        }
    }
}
