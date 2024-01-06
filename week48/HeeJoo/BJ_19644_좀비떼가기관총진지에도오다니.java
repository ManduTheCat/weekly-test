package saturday.sat240106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 진지거리 L
 * 진지로부터 im만큼 떨어진 곳에 있는 좀비의 체력은 Z_i ~ 0이하가 되면 영구사망
 * 좀비가 1m 이동할 때 기관총 또는 지뢰를 한 번 사용할 수 있음
 * 지뢰를 격발하는 경우 진지 밑으로 숨어야 함
 * 기관총 : 유효 사거리(M_L) 내의 모든 좀비의 체력을 M_k만큼 감소 ~ 무한대
 * 지뢰 : 유효 사거리(1m) 내 좀비는 체력과 상관없이 제압 가능 ~ C개 소유
 * 1m 떨어진 길 위의 좀비를 제압못하면 사망 ... 과연 킬로와 헥토는 살아남을 수 있을까 ?
 */
public class BJ_19644_좀비떼가기관총진지에도오다니 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine()); // 진지거리

        String[] inputs = br.readLine().split(" ");
        int ML = Integer.parseInt(inputs[0]); // 기관총 유효 사거리
        int MK = Integer.parseInt(inputs[1]); // 기관총 데미지

        int C = Integer.parseInt(br.readLine()); // 지뢰 개수

        int[] zombies = new int[L + 1];
        for(int i = 1; i <= L; i++) {
            zombies[i] = Integer.parseInt(br.readLine());
        }

        // 1m의 좀비 체력이 MK보다 낮으면 사격
        // 높으면 지뢰
        int bomb = ML - 1; // 사정거리 내 빈 칸 + 사용한 폭탄의 개수

        // 처음에 left = -ML + 1 했다가 틀렸음 ...
        // right가 1부터 시작하므로 -ML + 1 + 1
        int left = -ML + 2, right = 1;

        boolean[] bombs = new boolean[L + 1]; // idx번째 좀비에게 폭탄 사용 여부
        int dam = 0;
        while(right <= L) {
            dam = MK * ML - MK * bomb; // 현재 좀비에게 들어가는 데미지

            if(dam < zombies[right]) { // 지뢰를 써야만 하는 경우
                if(C <= 0) { // 지뢰 부족
                    System.out.println("NO");
                    System.exit(0);
                } else {
                    C--;
                    bomb++;
                    bombs[right] = true;
                }
            }

            if(left <= 0 || bombs[left]) { // 빈 칸 제외 or 폭탄이 범위에서 벗어남
                bomb--;
            }

            left++;
            right++;
        }

        System.out.println("YES");
    }
}
