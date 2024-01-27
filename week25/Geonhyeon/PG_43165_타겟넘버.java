class Solution {
    static int tar;
    static int ans;
    static int lns;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        ans = 0;
        tar = target;
        lns = numbers.length;
        
        dfs(numbers,0,0);
        
        return ans;
    }
    
    public void dfs(int[]numbers, int idx,int sum){
        if(idx == lns){
            if(sum == tar) ans++;
            return;
        }
        dfs(numbers, idx+1, sum+numbers[idx]);
        dfs(numbers, idx+1, sum-numbers[idx]);
    }
    
}
