class Solution {
    public int solution(String name) {
        int answer = 0;
        int cur = 0;
        int[] cost = new int[name.length()];
        for(int i=0;i<cost.length;i++)
            cost[i] = change(name.charAt(i));
        int len = name.length();
        int move = len-1;
        for(int i=0;i<len;i++){
            answer+=cost[i];
            cost[i] = 0;
            if(i<len-1 && name.charAt(i+1)=='A'){
                int nonA = i+1;
                while(nonA<len && name.charAt(nonA)=='A') nonA++;
                move = Math.min(move, i*2 + (len-nonA));
                move = Math.min(move, i+ (len-nonA)*2);
            } 
        }
        return answer+move;
    }
    /*알파벳 변경 시 최소 횟수*/
    int change(char target){
        int up = target-'A';
        int down = 'Z'-target+1;
        return Math.min(down,up);
    }
}
