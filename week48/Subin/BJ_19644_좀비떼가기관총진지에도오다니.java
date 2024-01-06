import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_19644_좀비떼가기관총진지에도오다니 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ml = Integer.parseInt(st.nextToken());
        int mk = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(br.readLine());

        int[] zombies = new int[l];
        for (int i = 0; i < l; i++) {
            zombies[i] = Integer.parseInt(br.readLine());
        }

        int[] sum = new int[l]; // 누적합
        boolean avail = true;
        
        for (int i = 0; i < l; i++) {
            if (i > 0) sum[i] = sum[i - 1]; // 누적합

            // 데미지 구하기
            // 기관총의 범위 내에서만 데미지를 받아야하므로 구간을 자른다 sum[i] - sum[i - ml]
            int damage = sum[i];
            if (i > ml) damage -= sum[i - ml];
            damage *= mk;

            if (zombies[i] - damage <= mk) { // 기관총을 사용할 수 있다면 사용한다.
                ++sum[i]; // 기관총 횟수 증가
            } else { // 기관총을 사용할 수 없다면 지뢰를 사용한다.
                if (c == 0) { // 지뢰가 없다면 클리어 불가능
                    avail = false;
                    break;
                }
                --c;
            }
        }

        System.out.println(avail ? "YES" : "NO");
    }

}
