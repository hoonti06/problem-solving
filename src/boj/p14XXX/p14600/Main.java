package boj.p14XXX.p14600;

import java.io.*;

public class Main {
    static int K;
    static int[][] board;
    static int num;

    static boolean check(int sr, int sc, int size) {
        for (int r = sr; r < sr + size; r++) {
            for (int c = sc; c < sc + size; c++) {
                if (board[r][c] != 0) return false;
            }
        }
        return true;
    }

    static void go(int r, int c, int size) {
        int curSz = size / 2;
        num++;
        if (check(r, c, curSz)) board[r + curSz - 1][c + curSz - 1] = num;
        if (check(r, c + curSz, curSz)) board[r + curSz - 1][c + curSz] = num;
        if (check(r + curSz, c, curSz)) board[r + curSz][c + curSz - 1] = num;
        if (check(r + curSz, c + curSz, curSz)) board[r + curSz][c + curSz] = num;
        if (size == 2) return;

        go(r, c, curSz);
        go(r, c + curSz, curSz);
        go(r + curSz, c, curSz);
        go(r + curSz, c + curSz, curSz);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        num = 0;

        K = Integer.parseInt(in.readLine());
        String[] split = in.readLine().split(" ");

        int N = 1 << K;
        board = new int[N][N];
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        int r = N - y;
        int c = x - 1;
        board[r][c] = -1;

        go(0, 0, N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
//        System.out.println(r +", " + c);

    }
}
