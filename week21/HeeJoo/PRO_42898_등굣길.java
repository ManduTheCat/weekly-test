package saturday;

/**
 * m x n 크기의 격자
 * 가장 왼쪽 위(1, 1)은 집의 좌표
 * 가장 오른쪽 아래(m, n)은 학교의 좌표
 * "오른쪽"과 "아래쪽"으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를
 * "1,000,000,007"로 나눈 나머지를 return하는 solution 함수
 * 1 <= 자연수 m, n <= 100. m과 n이 모두 1인 경우는 주어지지 않음
 * 물에 잠긴 지역은 0개 이상 10개 이하
 */
public class PRO_42898_등굣길 {
    static int MOD = 1000000007;
    public static void main(String[] args) {
        int m = 4, n = 3;
        int[][] puddles = new int[][]{{2, 2}};
        System.out.println(solution(m, n, puddles)); // 4
    }

    public static int solution(int m, int n, int[][] puddles) {
        // 지역 생성 및 웅덩이 표시
        int[][] map = new int[n + 1][m + 1];
        for (int[] puddle : puddles) {
            map[puddle[0]][puddle[1]] = -1;
        }

        map[1][1] = 1; // 시작점 집...
        int j = 2;
        for(int i = 1; i <= n; i++) {
            for(; j <= m; j++) {
                if(map[i][j] == -1) continue;

//                int up = j-1 < 1 ? 0 : map[i][j-1];
//                int right = i-1 < 1 ? 0 : map[i-1][j];
                map[i][j] = (map[i][j-1] == -1 ? 0 : map[i][j-1]) + (map[i-1][j] == -1 ? 0 : map[i-1][j]);
            }
            j = 1;
        }
        return map[n][m] % MOD; // 학교
    }
}
