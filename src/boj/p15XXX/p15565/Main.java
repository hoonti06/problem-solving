package boj.p15XXX.p15565;

import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] input;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        input = new int[N];
        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int ryanCnt = 0;
        int cur = 0;
        int prev = 0;
        while (ryanCnt < K && cur < N) {
            if (input[cur] == 1) {
                if (++ryanCnt == 1) {
                    prev = cur;
                }
            }
            cur++;
        }
        cur--;
        if (ryanCnt < K) {
            System.out.println(-1);
        } else {
            int res = cur - prev;
            while (cur < N) {
                cur++;
                while (cur < N && input[cur] == 2) {
                    cur++;
                }
                if (cur >= N) {
                    break;
                }
                prev++;
                while (prev <= cur && input[prev] == 2) {
                    prev++;
                }
                res = Math.min(res, cur - prev);
            }
            System.out.println(res + 1);
        }
    }
}
