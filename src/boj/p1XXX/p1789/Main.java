package boj.p1XXX.p1789;

import java.util.*;
import java.io.*;

public class Main {
    static long bsearch(long S) {
        long left = 1;
        long right = Integer.MAX_VALUE/2;
        long res = 1;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = mid * (mid + 1) / 2;
            if (sum <= S) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(bsearch(Long.parseLong(in.readLine())));
    }
}
