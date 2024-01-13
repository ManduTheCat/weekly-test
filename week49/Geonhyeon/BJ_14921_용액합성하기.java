/**
 * 투포인터?? -> 뭔가 할 수 있을 것같음.
 * 범위는 long까진 안해도됨.
 */
public class BJ_14921_용액합성하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0, right = n - 1;
        int min = Integer.MAX_VALUE;
        while (left < right) {
            int tmp = arr[left] + arr[right];
            if(Math.abs(min)> Math.abs(tmp)){
                min = tmp;
            }
            if(tmp==0) break;
            if (tmp > 0) right--;
            else left++;
        }
        System.out.println(min);
    }
}
