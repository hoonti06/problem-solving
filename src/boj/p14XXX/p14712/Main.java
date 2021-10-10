package boj.p14XXX.p14712;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, NM;
    static int res;
    static boolean[][] board;
    static int[] dx = {-1, -1,  0};
    static int[] dy = { 0, -1, -1};

    static int solution() {
        NM = N * M;
        backtracking(0);
        return res;
    }

    static void backtracking(int idx) {
        if (idx >= NM) {
            res++;
            return;
        }

        backtracking(idx+1);

        int r = idx / M;
        int c = idx % M;
        if (!isFull(r, c)) {
            board[r][c] = true;
            backtracking(idx+1);
            board[r][c] = false;
        }
    }

    static boolean isFull(int r, int c) {
        for (int i = 0; i < 3; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || !board[nr][nc]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new boolean[N][M];
        System.out.println(solution());
    }

}
