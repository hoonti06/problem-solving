package boj.p1XXX.p1062_가르침;

import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int res;
    static int[] inputBits;

    static void go(int cnt, int start, int bitmask) {
        if (cnt >= K) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                if ((inputBits[i] & bitmask) == inputBits[i]) {
                    count++;
                }
            }
            res = Math.max(res, count);
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((bitmask & (1 << i)) > 0) continue;
            go(cnt + 1, i + 1, bitmask | 1 << i);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        res = 0;

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K >= 26) {
            System.out.println(N);
            return;
        }

        inputBits = new int[N];
        for (int i = 0; i < N; i++) {
            String word = in.readLine();
            for (char ch : word.toCharArray()) {
                inputBits[i] |= 1 << ch - 'a';
            }
        }
        int bitmask = 0;
        bitmask |= 1 << ('a' - 'a');
        bitmask |= 1 << ('c' - 'a');
        bitmask |= 1 << ('n' - 'a');
        bitmask |= 1 << ('t' - 'a');
        bitmask |= 1 << ('i' - 'a');

        go(5, 0, bitmask);
        System.out.println(res);
    }
}