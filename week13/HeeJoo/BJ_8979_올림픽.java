import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Country{
    int gold, silver, bronze; // 금, 은, 동 메달 수

    public Country(int gold, int silver, int bronze){
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 국가의 수 N
        int K = Integer.parseInt(st.nextToken()); // 타겟 국가 K

        // N개의 줄에 각 국가의 금, 은, 동메달 수 입력
        Country target = null;
        Country[] array = new Country[N+1];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            if(num == K) target = new Country(gold, silver, bronze);

            array[num] = new Country(gold, silver, bronze);
        }

        int count = 0;
        for (int i = 1; i <= N; i++){
            if(i == K) continue;

            Country now = array[i];
            if(now.gold > target.gold) count++;
            else if(now.gold == target.gold){
                if(now.silver > target.silver) count++;
                else if(now.silver == target.silver){
                    if(now.bronze > target.bronze) count++;
                }
            }
        }

        System.out.println(count + 1);
    }
}
