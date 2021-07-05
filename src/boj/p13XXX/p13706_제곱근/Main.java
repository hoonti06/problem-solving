package boj.p13XXX.p13706_제곱근;

import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BigInteger dest = new BigInteger(in.readLine());

        BigInteger two = BigInteger.valueOf(2);
        BigInteger left = BigInteger.valueOf(1);
        BigInteger right = dest;
        while (left.compareTo(right) <= 0) {
            BigInteger mid = left.add(right).divide(two);
            BigInteger square = mid.multiply(mid);
            if (square.equals(dest)) {
                System.out.println(mid);
                break;
            } else if (square.compareTo(dest) < 0)
                left = mid.add(BigInteger.ONE);
            else
                right = mid.subtract(BigInteger.ONE);
        }
    }
}
