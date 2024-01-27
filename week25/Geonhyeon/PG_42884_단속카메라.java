import java.util.Arrays;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        //정렬
        Arrays.sort(routes, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(a[0], b[0]);
            }
        });
        
        //초기화
        int current_camera_location = routes[0][1];
        answer++;
        
        //로직
        for(int i=1; i<routes.length;i++){
            if(routes[i][0]>current_camera_location){
                answer++;
                current_camera_location = routes[i][1];
            }else{
                current_camera_location = Math.min(routes[i][1],current_camera_location);
            }
        }
        
        return answer;
    }
}
