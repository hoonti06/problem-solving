package boj.p1XXX.p1062_가르침;

import java.io.*;
import java.util.*;

public class Main2 {
    static int N, K;
    static int[] inputBitmask;

    static int go(int cnt, int start, int bitmask) {
        if (cnt >= K) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                if ((inputBitmask[i] & bitmask) == inputBitmask[i]) {
                    count++;
                }
            }
            return count;
        }

        int max = 0;
        for (int i = start; i < 26; i++) {
            int curBitmask = 1 << i;
            if ((bitmask & curBitmask) > 0) continue;
            int ret = go(cnt + 1, i + 1, bitmask | curBitmask);
            max = Math.max(max, ret);
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

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

        inputBitmask = new int[N];
        for (int i = 0; i < N; i++) {
            String word = in.readLine();
            for (char ch : word.toCharArray()) {
                inputBitmask[i] |= 1 << ch - 'a';
            }
        }
        int bitmask = 0;
        bitmask |= 1 << ('a' - 'a');
        bitmask |= 1 << ('c' - 'a');
        bitmask |= 1 << ('n' - 'a');
        bitmask |= 1 << ('t' - 'a');
        bitmask |= 1 << ('i' - 'a');

        System.out.println(go(5, 0, bitmask));
    }
}