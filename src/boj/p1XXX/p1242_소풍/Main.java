package boj.p1XXX.p1242_소풍;

import java.util.*;
import java.io.*;

public class Main {
    static int N, K, M;
    static List<Integer> list;

    static void solution() {
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for (int i = 1; i <= N; i++) list.add(i);

        solution();
//        System.out.println(solution());
    }
}
