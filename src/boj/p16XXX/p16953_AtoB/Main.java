package boj.p16XXX.p16953_AtoB;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder A, B;

    static int solution() {
        int cnt = 1;
        while (B.length() > 0) {
            if (A.toString().equals(B.toString()))
                return cnt;

            int numB = Integer.parseInt(B.toString());
            if (numB % 2 == 0) {
                numB /= 2;
                B.setLength(0);
                B.append(numB);
            }
            else if(B.charAt(B.length()-1) == '1')
                B.setLength(B.length()-1);
            else return -1;
            cnt++;
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        A = new StringBuilder(st.nextToken());
        B = new StringBuilder(st.nextToken());

        System.out.println(solution());
    }
}
