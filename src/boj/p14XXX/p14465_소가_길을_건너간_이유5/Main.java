package boj.p14XXX.p14465_소가_길을_건너간_이유5;

import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static boolean[] board;

    static int solution() {
        int res = 0, sum = 0;
        for (int i = 1; i <= K; i++)
            if (board[i]) sum++;

        res = sum;
        for (int cur = K+1, prev = 1; cur <= N; cur++, prev++) {
            if (board[prev]) sum--;
            if (board[cur]) sum++;
            res = Math.min(res, sum);
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        board = new boolean[N+1];
        for (int i = 0; i < B; i++) {
            int num = Integer.parseInt(in.readLine());
            board[num] = true;
        }
        System.out.println(solution());
    }
}
