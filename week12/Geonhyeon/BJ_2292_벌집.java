import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.StringTokenizer;


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int i=1;
		int sum = 1;
		while(N>sum) {
			int diff = 6*i;
			sum+=diff;
			i++;
			//System.out.println(sum);
		}
		bw.write(String.valueOf(i));
		
		bw.flush();
		bw.close();
		br.close();
		
	}
}
