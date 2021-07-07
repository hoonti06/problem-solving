package boj.p2XXX.p2417;

import java.io.*;
import java.math.*;

public class Main {

    static long bsearch(long n) {
//        BigInteger left = BigInteger.valueOf(0L);
//        BigInteger right = BigInteger.valueOf(Long.MAX_VALUE);
        BigInteger num = BigInteger.valueOf(n);
        long left = 0;
//        BigInteger right = BigInteger.valueOf(Long.MAX_VALUE);
        long right = Long.MAX_VALUE;
//        long right = (long)Integer.MAX_VALUE * 2;
//        long right = (long)Math.sqrt(Long.MAX_VALUE) * 2;
        long res = Long.MAX_VALUE - 1;
        while (left <= right) {
            long mid = (left + right) / 2;
//            long square = mid * mid;
            BigInteger midi = BigInteger.valueOf(mid);
            BigInteger square = midi.multiply(midi);
            if (num.compareTo(square) <= 0) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(bsearch(Long.parseLong(in.readLine())));
//        long a = Long.MAX_VALUE - 1;
//        System.out.println(bsearch(a));
    }
}
