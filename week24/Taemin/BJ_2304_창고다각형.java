import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Pillar implements Comparable<Pillar> {
    int left;
    int height;

    public Pillar(int left, int height) {
        this.left = left;
        this.height = height;
    }

    @Override
    public int compareTo(Pillar other) {
        return Integer.compare(this.left, other.left);
    }
}

public class BJ_2304_창고다각형 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        /* 입력 기둥 수 */
        int numOfPillar = 0;
        numOfPillar = Integer.parseInt(br.readLine());

        /* 기둥 리스트 */
        LinkedList<Pillar> pillars = new LinkedList<>();
        for (int i = 0; i < numOfPillar; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            pillars.add(new Pillar(left, height));
        }

        /* 각 기둥에서의 높이 결과 */
        int[] result = new int[numOfPillar];

        /* left 기준 오름차순 정렬 */
        Collections.sort(pillars);
        int maxIdx = 0;
        int maxHeight = 0;
        for (int i = 0; i < numOfPillar; i++) {
            Pillar pillar = pillars.get(i);
            int height = pillar.height;
            result[i] = height;

            /* 최대 높이 기둥 위치 */
            if (maxHeight < height) {
                maxIdx = i;
                maxHeight = height;
            }
        }

        /* 좌측 */
        int compare = 0;
        for (int i = 0; i < maxIdx; i++) {
            Pillar cur = pillars.get(i);
            int height = cur.height;

            if (compare <= height) {
                result[i] = height;
                compare = height;
            }
            else {
                result[i] = compare;
            }
        }

        /* 우측 */
        compare = 0;
        for (int i = numOfPillar - 1; i > maxIdx; i--) {
            Pillar cur = pillars.get(i);
            int height = cur.height;

            if (compare <= height) {
                result[i] = height;
                compare = height;
            }
            else {
                result[i] = compare;
            }
        }

        int total = 0;
        /* 좌측 결과 */
        for (int i = 0; i < maxIdx; i++) {
            Pillar cur = pillars.get(i);
            Pillar next = pillars.get(i+1);

            int count = next.left - cur.left;
            total += (count * result[i]);
        }

        /* 최대 높이 */
        total += maxHeight;
        
        /* 우측 결과 */
        for (int i = numOfPillar - 1; i > maxIdx; i--) {
            Pillar cur = pillars.get(i);
            Pillar prior = pillars.get(i - 1);

            int count = cur.left - prior.left;
            total += (count * result[i]);
        }

        System.out.println(total);
    }
}
