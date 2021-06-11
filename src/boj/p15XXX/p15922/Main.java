package boj.p15XXX.p15922;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int minLeft = Integer.parseInt(st.nextToken());
        int maxRight = Integer.parseInt(st.nextToken());

        int sum = 0;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            if (maxRight < left) {
                sum += maxRight - minLeft;
                minLeft = left;
                maxRight = right;
            } else if (maxRight < right) {
                maxRight = right;
            }
        }
        sum += maxRight - minLeft;
        System.out.println(sum);
    }
}
