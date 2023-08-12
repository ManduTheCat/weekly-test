class Solution {
    
    public int score;
    public int[] answer;
    
    public int[] solution(int n, int[] info) {
        
        score = 0;
        answer = new int[]{ -1 };
        combi(n, 0, info, new int[11]);
        
        return answer;
    }
    
    public void combi(int n, int index, int[] info, int[] store) {
        
        if (index == 11) {
            
            // 어피치가 이긴 경우 스킵
            int localScore = calScore(info, store);
            if (localScore <= 0) return;
            
            // 라이언 점수 비교
            if (score == localScore) {
                // 가장 낮은 점수 비교
                for (int i = 10; i >= 0; i--) {
                    
                    if (store[i] !=0 && answer[i] != 0) {
                        if (store[i] > answer[i]) {
                            answer = copy(store);
                            break;
                        }
                        else if (store[i] < answer[i]) {
                            break;
                        }
                    }
                    else if (store[i] != 0 && answer[i] == 0) {
                        answer = copy(store);
                        break;
                        
                    }
                    else if (store[i] == 0 && answer[i] != 0) {
                        break;
                    }
                }
                
            } else if (score < localScore) {
                answer = copy(store);
                score = localScore;
            }
            
            return;
        }
        
        
        for (int i = index; i < 11; i++) {
            
            if (info[i] + 1 <= n) {
                store[i] = info[i] + 1;
                combi(n - store[i], i + 1, info, store);
                store[i] = 0;
            }
            
            if (i == 10) {
                store[10] = n;
                combi(0, i + 1, info, store);
                store[10] = 0;
            }
        }
        
    }
    
    public int calScore(int[] info, int[] store) {
        
        int total = 0;
        for (int i = 0; i <= 10; i++) {
            int score = 10 - i;
            
            if (info[i] == 0 && store[i] == 0) continue;
            
            if (info[i] < store[i]) {
                total += score;
            } else {
                total -= score;
            }
        }
        
        return total;        
    }
    
    public int[] copy(int[] store) {
        int[] result = new int[11];
        for (int i = 0; i <= 10; i++) {
            result[i] = store[i];
        }
        
        return result;
    }
    
}
