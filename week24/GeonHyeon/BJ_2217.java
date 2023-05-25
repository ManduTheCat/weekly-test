import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static class MyScanner {
        BufferedReader bf;
        StringTokenizer st;
        MyScanner() {
            bf = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(bf.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }


    public static void main(String[] args) throws IOException {
        MyScanner sc = new MyScanner();
        int n = sc.nextInt();
        int[] ropes = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) 
            ropes[i] = sc.nextInt();
        
        Arrays.sort(ropes);
        for (int i = 0; i < n; i++) 
            if(max<ropes[i]*(n-i)) max = ropes[i]*(n-i);
        
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
