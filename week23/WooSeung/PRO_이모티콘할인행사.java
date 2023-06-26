class Solution {
    
    static int[] discount = {10, 20, 30, 40};
    static int maxService;
    static int maxCost;
    
    public int[] solution(int[][] users, int[] emoticons) {
        dfs(0, emoticons.length, new int[emoticons.length], users, emoticons);   
        return new int[]{maxService, maxCost};
    }
    
    static void dfs(int index, int depth, int[] discounts, int[][] users, int[] emoticons) {
    
        if(depth == index) {
            int service = 0;
            int cost = 0;
            
            for(int[] user: users) {
                int userDiscountRate = user[0];
                int userMaxCost = user[1];
                
                int sum = 0;
                
                for(int i = 0; i < emoticons.length; i++) {
                    if(discounts[i] >= userDiscountRate) {
                        sum += emoticons[i]/100*(100-discounts[i]);
                    }
                }
                if(sum >= userMaxCost){
                    service++;
                }else {
                    cost += sum;
                }
            }
            if(service > maxService) {
                maxService = service;
                maxCost = cost;
            }else if(service == maxService) {
                maxCost = Math.max(maxCost, cost);
            }
            return;
            
        }
        
        for(int i = 0; i < 4; i++) {
            discounts[index] = discount[i];
            dfs(index + 1, emoticons.length, discounts, users, emoticons);
        }
        
    }
    
}
