package boj.p11660_구간합_구하기5;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board, sum;

    static void solution() {
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                sum[i][j] = board[i][j] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        sum = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 1; j <= N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        solution();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            sb.append(sum[r2][c2] - sum[r2][c1-1] - sum[r1-1][c2] + sum[r1-1][c1-1]).append("\n");
        }
        System.out.println(sb);
    }
}
