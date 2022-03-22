package boj.p2XXX.p2447;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] board;

    static void go(int r, int c, int size) {
        if (size <= 1) {
            board[r][c] = '*';
            return;
        }

        int nextSize = size / 3;
        for (int nr = r, i = 0; i < 3; i++, nr += nextSize) {
            for (int nc = c, j = 0; j < 3; j++, nc += nextSize) {
                if (i == 1 && j == 1) continue;
                go(nr, nc, nextSize);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], ' ');
        }

        go(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
