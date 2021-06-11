package swea.p3238_이항계수;

import java.io.*;
import java.util.*;

public class Solution {

    static int N, R;
    static int P;
    static long[] fac;

    static long factorial(int i) {
        if (i == 1) fac[1] = 1L;
        if (fac[i] != 0) return fac[i];

        return fac[i] = i * factorial(i-1) % P;
    }

    static long pow(long n, int x) {
        if (x == 0) return 1L;
        long tmp = pow(n, x/2);
        long ret = (tmp * tmp) % P;
        if (x % 2 == 0) return ret;
        else return (ret * n) % P;
    }
    static long solution() {
        fac[0] = 1;
        for (int i = 1; i <= N; i++)
            fac[i] = (fac[i - 1] * i) % P;

        long res = fac[N] % P;
//        System.out.println(factorial(5));
        long bottom = fac[R] * fac[N-R] % P;
        res = (res * pow(bottom, P-2)) % P;
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
            P = Integer.parseInt(line[2]);
            Long ln = (Long.parseLong(line[0]) % P);
            System.out.println(ln);
            N = (int) (Long.parseLong(line[0]) % P);
            R = (int) (Long.parseLong(line[1]) % P);
            fac = new long[N + 1];
            System.out.println(4256410 % 89329);
//            N = Integer.parseInt(line[0]);
//            int length = line[2].length();
//            if (length < line[0].length()) {
//                N = Integer.parseInt(line[0].substring(line[0].length()-length));
//            }
//            else
//                N = Integer.parseInt(line[0]);
//            fac = new long[N+1];
//            if (length < line[1].length()) {
////                N = System.out.println(line[0].substring(line[0].length()-length));
//                R = Integer.parseInt(line[1].substring(line[1].length()-length));
//            } else
//                R = Integer.parseInt(line[1]);
////            N = Long.parseLong(line[0]);
////            R = Long.parseLong(line[1]);
//            P = Integer.parseInt(line[2]);
            System.out.println("#" + tc + " " + solution());
        }
    }
}
