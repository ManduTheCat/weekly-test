import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10431_줄세우기 {

    // 키 순서대로 같은 키의 아이들은 없다
    // 반 아이들은 항상 20명
    // 자기 앞에 자기보다 키가 큰 학생이 없다면 그냥 그 자리에 서고 차례 종료
    // 자기 앞에 자기보다 키가 큰 학생이 한 명 이상 있다면 그중 가장 앞에 있는 학생(A)의 바로 앞에 선다
    // 이때, A부터 그 뒤의 모든 학생들은 공간을 만들기 위해 한 발씩 뒤로 물러서게 된다.
    public static void main(String[] args) throws IOException {

        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test = 0; test < T; test++) {

            st = new StringTokenizer(br.readLine());

            int test_num = Integer.parseInt(st.nextToken());
            int count = 0;

            int[] arr = new int[20];

           for(int i = 0; i < arr.length; i++) {
               arr[i] = Integer.parseInt(st.nextToken());
           }

           for(int i = 0; i < arr.length; i++) {
               for(int j = 0; j < i; j++) {
                   if(arr[j] > arr[i]) {
                       count++;
                   }
               }
           }

            System.out.println(test_num + " " + count);
        }

    }

}
