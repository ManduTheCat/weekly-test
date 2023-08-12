class Solution {
    private int max;
    private int[] maxResult;
    
    public int[] solution(int n, int[] info) {
        // 초기화
        maxResult = new int[1];
        maxResult[0] = -1;
        
        // dfs
        dfs(0, n, new int[info.length], info);
        
        return maxResult;
    }
    
    private void dfs(int cnt, int n, int[] result, int[] info) {
        // 화살이 남지 않았거나 모든 점수를 확인한 경우
        if (n == 0 || result.length == cnt) { 
            int[] score = getScore(result, info);
            // 라이언의 점수가 어피치의 점수 이하거나, 두 점수의 차가 이전보다 작다면 return
            if (score[0] <= score[1] || max > score[0] - score[1]) return;
            // 점수가 같을 경우 가장 낮은 점수를 가장 많이 맞힌 경우여야 함
            if (max == score[0] - score[1]) {
                for (int i = 10; i >= 0; i--) {
                    if (result[i] > maxResult[i]) break;
                    if (result[i] < maxResult[i]) return;
                }
            }
            
            max = score[0] - score[1];
            maxResult = result.clone();
            // 화살은 모두 사용
            if (n != 0) maxResult[cnt - 1] = n;
            return ;
        }
        
        // 점수 획득
        if (info[cnt] < n) {
            result[cnt] += info[cnt] + 1;
            dfs(cnt + 1, n - (info[cnt] + 1), result, info);
            result[cnt] -= info[cnt] + 1;
        }
        // 점수 미획득
        dfs(cnt + 1, n, result, info);
        
    }
    
    // 점수 구하기
    private int[] getScore(int[] result, int[] info) {
        int[] score = new int[2];
        for (int i = 0; i < result.length; i++) {
            if (info[i] == 0 && result[i] == 0) continue;
            
            // 더 많이 화살을 맞춘 선수가 점수 획득
            if (info[i] < result[i]) score[0] += 10 - i;
            else score[1] += 10 - i;
        }
        return score;
    }
}
