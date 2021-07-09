package boj.p14XXX.p14929;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static long sum;
    static int[] input;
    static long solution() {
        long res = 0;
        for (int i = 0; i < N-1; i++) {
            sum -= input[i];
            res += input[i] * sum;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        input = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            sum += input[i];
        }

        System.out.println(solution());
    }
}
