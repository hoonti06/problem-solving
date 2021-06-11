package swea.p5607_조합;

import java.io.*;
import java.util.*;

public class Solution {
    static int N, R;
    static final int MOD = 1234567891;
    static long[] fac;

    static long factorial(int i) {
        if (i == 1) fac[1] = 1L;
        if (fac[i] != 0) return fac[i];

        return fac[i] = i * factorial(i-1) % MOD;
    }

    static long pow(long n, int x) {
        if (x == 0) return 1L;
        long tmp = pow(n, x/2);
        long ret = (tmp * tmp) % MOD;
        if (x % 2 == 0) return ret;
        else return (ret * n) % MOD;
    }
    static long solution() {
        fac[0] = 1;
        for (int i = 1; i <= N; i++)
            fac[i] = (fac[i - 1] * i) % MOD;

        long res = fac[N] % MOD;
//        System.out.println(factorial(5));
        long bottom = fac[R] * fac[N-R] % MOD;
        res = (res * pow(bottom, MOD-2)) % MOD;
//        res = (res * pow(factorial(N-R), MOD-2)) % MOD;
        return res;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int testcase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            String[] line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            R = Integer.parseInt(line[1]);
            fac = new long[N+1];
            System.out.println("#" + tc + " " + solution());
        }
    }
}
