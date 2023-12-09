package com.kim.date1209;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
투포인터
홀수를 만날 때 제거하고 가장 앞에있는 홀수 인덱스를 다시붙이기 반복  
*/
public class BJ_22862_가장_긴_짝수_연속한_부분_수열 {
    public static void main(String[] args) throws IOException {
        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int len = 0;
        int max = 0;

        //logic
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num % 2 == 0) {
                len++;
            } else if(q.size()<K){
                q.offer(i);
            }else{
                len = i-q.poll()-K;
                q.offer(i);
            }
//            System.out.println("i:" + i + " len:" + len);
            max = Math.max(max, len);
        }

        //print
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
