package boj.p17XXX.p17609;

import java.io.*;

public class Main {
    static int T;
    static char[] chars;

    static int solution(String str) {
        chars = str.toCharArray();
        int N = chars.length;
        int ret = go(0, 0, N-1);
        return ret;
    }

    static int go(int cnt, int left, int right) {
        if (left >= right) {
            return cnt;
        }

        if (chars[left] != chars[right]) {
            if (++cnt >= 2) {
                return 2;
            }
            int ret1 = go(cnt, left+1, right);
            int ret2 = go(cnt, left, right-1);
            return Math.min(ret1, ret2);
        } else {
            int ret = go(cnt, left+1, right-1);
            return ret;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(in.readLine());
        for (int i = 0; i < T; i++) {
            System.out.println(solution(in.readLine()));
        }
    }
}
