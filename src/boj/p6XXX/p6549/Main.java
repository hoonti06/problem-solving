package boj.p6XXX.p6549;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static final int MAX_N = 100010;
    static int[] input = new int[MAX_N];

    static void solution() {

    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        while (true) {
//            String[] splited = in.readLine().split(" ");
//            N = splited[0];
//            if (N == 0) break;
//
//            for (int i = 0; i < N; i++) {
//                input[i] = Integer.parseInt(splited[i+1]);
//            }
//            solution();
//        }

        StringBuilder sb = new StringBuilder();
        sb.append("hi");
        System.out.println(sb);

        sb.reverse();
        System.out.println(sb);
    }
}
