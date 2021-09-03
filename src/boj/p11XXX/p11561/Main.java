package boj.p11XXX.p11561;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            long N = Long.parseLong(in.readLine());

            long res = 1;
            long left = 1;
            long right = (long) 2e8;
            while (left <= right) {
                long mid = (left + right) / 2;
                long mul = mid * (mid + 1)/2;
                if (mul <= N) {
                    res = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            System.out.println(res);
        }
    }
}
