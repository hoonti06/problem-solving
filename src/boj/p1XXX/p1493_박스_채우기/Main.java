package boj.p1XXX.p1493_박스_채우기;

import java.io.*;
import java.util.*;

public class Main {
    static int L, W, H, N;
    static int[] cnts;
    static boolean solved;
    static int res;

    static void go(int l, int w, int h, int idx) {
        if (l <= 0 || w <= 0 || h <= 0) {
            return;
        }

        int cur = idx;
        for (; cur >= 0; cur--) {
            int len = 1 << cur;
            if (cnts[cur] <= 0 || len > l || len > w || len > h)
                continue;

            cnts[cur]--;
            res++;
            go(l, w, h - len, cur);
            go(l - len, len, len, cur);
            go(l, w - len, len, cur);
            break;
        }
        if (cur == -1) {
            solved = false;
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        solved = true;
        res = 0;

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(in.readLine());
        cnts = new int[20];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            cnts[idx] = cnt;
        }

        go(L, W, H, N-1);
        System.out.println(solved? res : -1);
    }

}
