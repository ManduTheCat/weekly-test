// n명의 멘토
// 1 ~ k개의 상담 유형
// 각 멘토는 k개의 상담 유형 중 하나만 담당
// 상담을 원하는 참가자가 상담 요청을 했을 때, 참가자의 상담 유형을 담당하는 멘토 중 상담 중이 아닌 멘토와 상담을 시작합니다.
// 만약 참가자의 상담 유형을 담당하는 멘토가 모두 상담 중이라면, 자신의 차례가 올 때까지 기다립니다. 참가자가 기다린 시간은 참가자가 상담 요청했을 때부터 멘토와 상담을 시작할 때까지의 시간입니다.
// 모든 멘토는 상담이 끝났을 때 자신의 상담 유형의 상담을 받기 위해 기다리고 있는 참가자가 있으면 즉시 상담을 시작합니다. 이때, 먼저 상담 요청한 참가자가 우선됩니다.

// 참가자가 상담을 요청했을 때부터 상담을 시작하기까지 기다린 시간의 합이 최소가 되도록 각 상담 유형별로 멘토 인원을 정하려 합니다. 단, 각 유형별로 멘토 인원이 적어도 한 명 이상이어야 합니다.
class Solution {
    
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int k, int n, int[][] reqs) {
        
        comb(k, n, 0, 0, new int[k], reqs);
        
        return answer;
    }
    
    void func(int k, int[] selected, int[][] reqs) {
        // selected 2 1 2 상담원 배치 현황 1번 유형 2명, 2번 유형 1명, 3번 유형 2명
        
        int[][] consult = new int[k][];
        // 걸린 시간을 담을 변수 생성
        int time = 0;
        
        
        // 상담을 진행할 멘토 배열 생성
        for(int i = 0; i < k; i++) {
            //consult[i]에 해당하는 멘토의 인원수 만틈 배치
            consult[i] = new int[selected[i]];
        }
        // 배치된 멘토로 진행했을때 걸리는 시간을 측정
        for(int[] req : reqs) {
            // 상담 유형을 고르기 위해서 req[2] - 1을 type변수에 설정
            int type = req[2] - 1;
            // 가장 대기 시간이 적은 인덱스를 저장
            int minIdx = 0;
            // 상담 진행
            for(int i = 0; i < selected[type]; i++) {
                // 바로 상담이 가능한 경우 시각 + 상담 시각을 저장
                if(consult[type][i] <= req[0]) {
                    consult[type][i] = req[0] + req[1];
                    break;
                }
                // minIdx 갱신
                if(consult[type][i] < consult[type][minIdx]) {
                    minIdx = i;
                }
                // 만약 바로 상담을 진행하지 못한 경우 대기 시간이 최소인 배열에 상담을 진행해야 한다.
                if(i == selected[type] - 1) { 
                    // 대기 시간을 time 변수에 더 해준다.
                    time += consult[type][minIdx] - req[0];
                    // 상담 시간 갱신
                    consult[type][minIdx] += req[1];
                }
            }
            
        }
        // 가장 적게 대기 시간이 걸린 경우를 answer에 저장
        answer = Math.min(answer, time);
    }
    
     void comb(int k, int n, int idx, int curSum, int[] selected, int[][] reqs) {
         // 모든 상담유형을 돌았으며 그때 선택한 멘토의 수가 n가 같으면 func 실행
        if (idx == k) {
            if (curSum == n) {
                func(k, selected, reqs);
            }
            return;
        }
        
        for (int i = 1; i <= n - curSum; i++) {
            selected[idx] = i;
            comb(k, n, idx + 1, curSum + i, selected, reqs);
        }
    }
    
}
