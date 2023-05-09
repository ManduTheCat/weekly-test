class Solution {
    
    // 이모티콘 플러스 서비스 가입자가 최대인 상황에서 판매액을 최대로 늘려라
    public int maxNumOfJoined = 0;
    public int maxMargin = 0;
    public int[] rates = { 10, 20, 30, 40 };
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        // users (최소 1명에서 최대 100명)
        int numOfUser = users.length;

        
        // emoticons (최소 1개에서 최대 7개)
        int numOfEmo = emoticons.length;

        
        // emoticon 마다 할인율을 배정하는 모든 경우의 수를 검사해야한다
        // emoticon의 길이 만큼에 해당하는 배열에 할인율 4개를 할당 
        // O(4^n)
        int[] array = new int[numOfEmo];
        DFS(0, numOfEmo, array, users, emoticons);
        
        
        int[] answer = {maxNumOfJoined, maxMargin};
        return answer;
    }
    
    public void DFS(int depth, int size, int[] array, int[][] users, int[] emoticons) {
        if (depth == size) {
            
            // array에 순서대로 각 emoticon에 할당된 할인율이 적혀있다
            int localMaxNumOfJoined = 0;
            int localMaxMargin = 0;
            
            for (int i = 0; i < users.length; i++) {
                int total = 0;
                
                for (int j = 0; j < emoticons.length; j++) {
                    double rate = rates[array[j]]; 
                    
                    // 일정 비율 미만 할인 이모티콘은 패스
                    if (rate  < users[i][0]) continue;
                    
                    int charge = (int)(emoticons[j] * (100 - rate) / 100); 
                    total += charge;
                    
                }
                
                // 총 구매 비용이 일정 가격 이상인 경우 구매 취소 및 이모티콘 플러스 가입
                if (total >= users[i][1]) {
                    localMaxNumOfJoined++;
                    total = 0;
                }
                
                localMaxMargin += total;
            }
            
            //System.out.printf("가입자 수: %d, 마진: %d\n", localMaxNumOfJoined, localMaxMargin);
            
            
            if (localMaxNumOfJoined > maxNumOfJoined) {
                maxNumOfJoined = localMaxNumOfJoined;
                maxMargin = localMaxMargin;
            } else if (localMaxNumOfJoined == maxNumOfJoined) {
                maxMargin = Math.max(maxMargin, localMaxMargin);
            }
            
            
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            array[depth] = i;
            DFS(depth + 1, size, array, users, emoticons);
        }
    }
}
