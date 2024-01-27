import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        ArrayList<Integer> ranks = new ArrayList<>();
        int[] wanho = scores[0];
        
        // 동료 평가 내림차순, 동차시 근무태도 오름차순
        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                
                return Integer.compare(o2[0], o1[0]);
            }
        });
        
        
        int attitude = Integer.MAX_VALUE;
        int peer = -1;
        
        for (int[] score : scores) {
            
            // 두 점수 모두 이전 기준 점수와 비교하여 낮은 경우
            if (attitude > score[0] && peer > score[1]) {
                // 만약 완호의 경우 -1을 리턴한다
                if (score[0] == wanho[0] && score[1] == wanho[1]) return -1;
            }
            else {
                // 통과한 점수를 순위 리스트에 넣는다
                ranks.add(score[0] + score[1]);
                
                if (peer < score[1]) {
                    // 새로운 동료 평가가 이전 동료 평가보다 크다면
                    // 태도 평가가 달라지는 경계값도 넘어가기 때문에 태도 기준 값을 갱신해준다
                    attitude = score[0];
                    peer = score[1];
                }
            }
        }
        
        // 내림 차순 정렬
        Collections.sort(ranks, Collections.reverseOrder());
        
        // 가장 먼저 찾은 좌표 + 1
        int answer = ranks.indexOf(wanho[0] + wanho[1]) + 1;
        return answer;
    }
}
