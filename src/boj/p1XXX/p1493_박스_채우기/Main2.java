package boj.p1XXX.p1493_박스_채우기;

import java.io.*;
import java.util.*;

public class Main2 {
    static int N, res;
    static int[] counts;
    static boolean solved;

    static void go(int idx, int l, int w, int h) {
        if (idx < 0) {
            if ((long) l * w * h > 0) {
                solved = false;
            }
            return;
        }

        int len = 1 << idx;
        if (counts[idx] <= 0 || l < len || w < len || h < len) {
            go(idx-1, l, w, h);
            return;
        }

        counts[idx]--;
        res++;
        go(idx, l, w, h - len);
        go(idx, l - len, len, len);
        go(idx, l, w - len, len);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        solved = true;
        res = 0;

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int L = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(in.readLine());
        counts = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            counts[idx] = count;
        }
        go(N-1, L, W, H);

        System.out.println(solved? res : -1);
    }
}
