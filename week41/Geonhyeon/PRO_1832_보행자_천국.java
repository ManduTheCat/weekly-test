/*
각각의 Point가 위와 왼쪽에서 오는 경우의 수를 구분
*/
class Solution {
    static int MOD = 20170805;
    static int answer = 0;
    static Point[][] map;
    
    public int solution(int m, int n, int[][] cityMap) {
        //행과 열을 한줄씩 추가 (ArrayINdexOutOfBoundsException 방지 패딩)
        map = new Point[m+1][n+1];
        
        //map초기화
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                map[i][j] = new Point(1,0,0);
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                map[i+1][j+1].val = cityMap[i][j];
            }
        }
        map[1][2].left = 1;
        map[2][1].up = 1;
        find();
        return answer;
    }
    
    static void find(){
        int m = map.length;
        int n = map[0].length;
        
        //열,행 하나씩 패딩을 넣었으니 1부터 탐색
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                //위와 왼쪽 탐색
                map[i][j].left += left(i,j);
                map[i][j].up += up(i,j);
            }
        }
        answer = (map[m-1][n-1].left + map[m-1][n-1].up)%MOD;
    }
    
    //왼쪽에서 해당지점에 올 수 있는 경우의 수 찾기
    static int left(int row, int col){
        Point p = map[row][col-1];
        if(p.val == 1) return 0; //통행 금지라면 왼쪽에서 나에게 올 수 있는 경우는 없음
        else if(p.val == 2){ //해당지점의 왼쪽이 2라면 왼쪽에서 온 것만 return
            return p.left%MOD;
        }else{
            return (p.left + p.up)%MOD;
        }
    }
    
    //위쪽에서 해당지점에 올 수 있는 경우의 수 찾기
    static int up(int row, int col){
        Point p = map[row-1][col];
        if(p.val == 1) return 0; //통행 금지라면 왼쪽에서 나에게 올 수 있는 경우는 없음
        else if(p.val == 2){ //해당지점의 위쪽이 2라면 위쪽에서 온 것만 return
            return p.up%MOD;
        }else{
            return (p.left + p.up)%MOD;
        }
    }
    
}

class Point{
    int val;
    int up,left;
    
    Point(int val,int up,int left){
        this.val = val;
        this.up = up;
        this.left= left;
    }
}
