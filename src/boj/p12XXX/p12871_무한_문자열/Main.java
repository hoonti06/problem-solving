package boj.p12XXX.p12871_무한_문자열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

    static String s, t;

    static int gcd(int a, int b) {
        int r = a % b;
        return r == 0? b : gcd(b, r);
    }

    static void solution() {
        int lcm = s.length() * t.length() / gcd(s.length(), t.length());

        StringBuilder sb1 = new StringBuilder(s);
        while (sb1.length() != lcm) sb1.append(s);

        StringBuilder sb2 = new StringBuilder(t);
        while (sb2.length() != lcm) sb2.append(t);

        System.out.println(sb1.toString().equals(sb2.toString())? 1 : 0);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        s = in.readLine();
        t = in.readLine();

        solution();
    }
}
