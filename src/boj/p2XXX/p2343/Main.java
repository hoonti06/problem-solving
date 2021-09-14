package boj.p2XXX.p2343;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] input;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        st = new StringTokenizer(in.readLine(), " ");
        long right = 0;
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            right += input[i];
        }
        long left = 1;
        long res = 1;
        while (left <= right) {
            long mid = (left + right) / 2;
            long cur = 0;
            int cnt = 1;
            boolean flag = true;
            for (int i = 0; i < N; i++) {
                if (input[i] > mid) {
                    flag = false;
                    break;
                }
                if (cur + input[i] <= mid) {
                    cur += input[i];
                } else {
                    cur = 0;
                    cnt++;
                    i--;
                }
            }

            if (!flag || cnt > M) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }
        System.out.println(res);
    }

}
