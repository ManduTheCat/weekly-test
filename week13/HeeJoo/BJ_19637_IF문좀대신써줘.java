import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
WEAK : 전투력 10,000 이하
NORMAL : 전투력 10,000 초과, 100,000 이하
STRONG : 전투력 100,000 초과, 1,000,000 이하
캐릭터의 전투력에 맞틑 칭호를 출력하는 프로그램
칭호가 여러 개인 경우 가장 먼저 입력된 칭호 하나만 출력
 */

class Title{
    String title; // 칭호
    int upperLimit; // 전투력 상한값

    public Title(String title, int upperLimit){
        this.title = title;
        this.upperLimit = upperLimit;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 칭호의 개수 N
        int M = Integer.parseInt(st.nextToken()); // 캐릭터들의 개수 M

        // N개의 줄에 각 칭호의 이름과 전투력 상한값 입력
        // 칭호는 전투력 상한값의 비내림차순으로 입력됨 ~ 앞의 원소보다 크거나 같다.
        Title[] titles = new Title[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            String title = st.nextToken();
            int upperLimit = Integer.parseInt(st.nextToken());

            titles[i] = new Title(title, upperLimit);
        }

        // M개의 캐릭터 전투력 입력
        for(int i = 0; i < M; i++){
            int power = Integer.parseInt(br.readLine());

            // 이분탐색
            int start = 0;
            int end = N-1;
            while(start <= end){
                int middle = (start + end) / 2;

                if(power > titles[middle].upperLimit){
                    start = middle + 1;
                } else if(power <= titles[middle].upperLimit){
                    end = middle - 1;
                }
            }
            sb.append(titles[start].title + "\n");
        }

        System.out.println(sb);
    }
}
