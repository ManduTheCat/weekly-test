class Solution {
    static int ans = 0;
    public int solution(int[] numbers, int target) {
        perm(0, 0, numbers, target);
        
        return ans;
    }
    
    public void perm(int index, int sum, int[] numbers, int target) {
        if(index == numbers.length) {
            if(sum == target) {
                ans++;
            }
            return;
        }        
        
        perm(index+1, sum+numbers[index], numbers, target);
        perm(index+1, sum-numbers[index], numbers, target);
    }
}
