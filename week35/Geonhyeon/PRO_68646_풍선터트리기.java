/*
검사하려는 수 기준 오른쪽 제일큰수 저장하는 배열(right)
왼쪽에 제일큰수 저장하는 배열(left)
mid를 기준 좌우 비교해서 left에도 작은수, right에도 작은수면 살아남을 수 없는 수
*/
class Solution {
    public int solution(int[] a) {
        if(a.length < 3) return a.length;
        int answer = 2;
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        int mid;
        
        //left배열 초기화
        int min = a[0];
        for(int i=0;i<left.length;i++){
            min = Math.min(min, a[i]);
            left[i] = min;
        }
        
        //right배열 초기화
        min = a[a.length-1];
        for(int i=right.length-1;i>=0;i--){
            min = Math.min(min, a[i]);
            right[i] = min;
        }
        
        
        for(int i=1;i<a.length-1;i++){
            mid = a[i];
            if(left[i-1]<mid&&right[i+1]<mid) continue;
            answer++;
        }
        
        return answer;
    }
}



/*
시도1.시간초과

기준이 되는 수의 왼쪽에서 제일 작은 수와 오른쪽에서 제일 작은수를 찾음
왼쪽수와 오른쪽 수 모두 기준 수보다 작으면 fail 나머진 성공
*/
class Solution {
    public int solution(int[] a) {
        if(a.length < 3) return a.length;
        int answer = 2;
        int mid=0,left=0,right=0;
        
        for(int i=1;i<a.length-1;i++){
            mid = a[i];
            //왼쪽에서 제일 작은 수 찾기
            left = find(0,i,a);
            right = find(i+1,a.length,a);
            
            if(right<mid && left<mid) continue;
            answer++;
        }
        return answer;
    }
    
    static int find(int from,int to, int[] a){
        int num = a[from];
        for(int i=from;i<to;i++){
            num = Math.min(num,a[i]);
        }
        return num;
    }
}



/* 
시도 2.시간초과
왼쪽 수를 매번 최솟값을 찾지 말고 누적으로 찾기. 오른쪽 수는?
*/
class Solution {
    public int solution(int[] a) {
        if(a.length < 3) return a.length;
        int answer = 2;
        int mid=0,left=a[0],right=find(2,a.length,a);
        
        for(int i=1;i<a.length-1;i++){
            mid = a[i];
            if(mid == right) right = find(i+1,a.length,a); //right가  mid가 되어버리면 갱신
            // System.out.println("left:"+left+" mid:"+mid+" right:"+right);
            if(right>mid || left>mid) {
                answer++;
            }
            if(mid < left) left = mid; //left 최솟값 갱신
        }
        return answer;
    }
    
    static int find(int from,int to, int[] a){
        int num = a[from];
        for(int i=from;i<to;i++){
            num = Math.min(num,a[i]);
        }
        return num;
    }
}

