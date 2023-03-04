import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10431_줄세우기 {

    static int P;
    static int Count;
    static int[] Array;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        P = Integer.parseInt(br.readLine());
        for (int t = 1; t <= P; t++) {
            st = new StringTokenizer(br.readLine());
            sb.append(st.nextToken() + " ");

            Count = 0;
            Array = new int[21];
            Array[0] = Integer.parseInt(st.nextToken());
            int idx = 1;

            while (st.hasMoreTokens()) {
                int height = Integer.parseInt(st.nextToken());
                int findIdx = find(height, idx);

                if (findIdx == -1) {
                    Array[idx] = height;
                } else {
                    move(findIdx, idx);
                    Array[findIdx] = height;
                    Count += (idx - findIdx);
                }

                idx++;
            }

            sb.append(Count + "\n");
        }

        System.out.println(sb.toString());
    }
    public static void move(int start, int end) {
        for (int i = end; i > start; i--) {
            Array[i] = Array[i - 1];
        }
    }
    public static int find(int height, int idx) {
        for (int i = 0; i < idx; i++) {
            if (Array[i] > height) {
                return i;
            }
        }

        return -1;
    }
}
