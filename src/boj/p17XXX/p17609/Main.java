package boj.p17XXX.p17609;

import java.io.*;

public class Main {
    static int N;

    static int go(char[] chars, int left, int right, int cur, int n, int cnt) {
        if (cur > n) {
            return cnt;
        }

        if (chars[left] == chars[right]) {
            return go(chars, left+1 , right-1, cur + 1, n, cnt);
        }

        if (cnt >= 1) {
            return 2;
        }

        int ret1 = go(chars, left+1, right, cur+1, n, cnt + 1);
        int ret2 = go(chars, left, right-1, cur+1, n, cnt + 1);
        return Math.min(ret1, ret2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            int len = str.length();
            int ret1 = go(str.toCharArray(), 0, len-1, 0, len/2, 0);
            int ret2 = 2;
            if (len >= 2) {
                int ret3 = go(str.toCharArray(), 1, len-1, 0, len/2, 1);
                int ret4 = go(str.toCharArray(), 0, len-2, 0, len/2, 1);
                ret2 = Math.min(ret3, ret4);
            }
            System.out.println(Math.min(ret1, ret2));
        }
    }
}
