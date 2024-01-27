import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1253_좋다 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0 ; i < N ; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        int result = 0;
        for(int i = 0 ; i < N ; i++){
            int start = 0;
            int end = N-1;    
            while(true){

                if(start == i) start++;
                else if(end == i) end--;

                if(start >= end) break;

                if(numbers[start] + numbers[end] > numbers[i]) end--;
                else if(numbers[start] + numbers[end] < numbers[i]) start++;
                else{    
                    result++;
                    break;
                }
            }
        }
        System.out.println(result);
    }

}
