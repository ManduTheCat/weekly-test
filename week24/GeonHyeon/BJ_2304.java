import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int columns[][] = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            columns[i][0] = Integer.parseInt(st.nextToken());
            columns[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(columns, (o1, o2) -> o1[0] - o2[0]); //인덱스 순서로 정렬
        int maxLeft = 0;
        int currentIdx = 0;
        int area = 0;

        for (int i = 0; i < N; i++) {
            if(columns[i][1]>=maxLeft){
                area+=columns[i][1]+ maxLeft*(columns[i][0] - currentIdx - 1);
                currentIdx = columns[i][0];
                maxLeft = columns[i][1];
            }else{
                int maxRight = columns[i][1];
                for(int j= i+1;j<N;j++){
                    maxRight = Math.max(maxRight, columns[j][1]);
                }
                int k = Math.min(maxRight, maxLeft);
                area+= (columns[i][0]-currentIdx)*k;
                currentIdx = columns[i][0];
            }
//            System.out.println(area);
        }

        System.out.println(area);
    }
}
