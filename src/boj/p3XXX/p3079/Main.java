package boj.p3XXX.p3079;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long M;
    static long[] input;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        input = new long[N];
        for (int i = 0; i < N; i++) {
            input[i] = Long.parseLong(in.readLine());
        }

        long left = 1;
        long right = (long) Math.pow(10, 18);

        long res = right;
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            for (int i = 0; i < N; i++) {
                long quot = mid / input[i];
                cnt += quot;
                if (cnt >= M) {
                    break;
                }
            }
            if (M <= cnt) {
                res = Math.min(res, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(res);
    }
}
