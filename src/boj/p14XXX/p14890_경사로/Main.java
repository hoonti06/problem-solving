package boj.p14XXX.p14890_경사로;

import java.io.*;
import java.util.*;

public class Main {
    static int N, X;
    static int[][] board;
    static boolean[][] visited;

    static boolean goRow(int r, int c) {
        if (c >= N-1) return true;

        int nc = c + 1;
        if (board[r][c] == board[r][nc])
            return goRow(r, nc);
        else if (board[r][c] == board[r][nc] + 1) { // down
            int comparison = board[r][nc];
            for (int nnc = nc; nnc < nc + X; nnc++) {
                if (nnc >= N || board[r][nnc] != comparison || visited[r][nnc])
                    return false;
                visited[r][nnc] = true;
            }
            return goRow(r, c + X);
        } else if (board[r][c] + 1 == board[r][nc]) { // up
            int comparison = board[r][c];
            for (int nnc = c; nnc > c - X; nnc--) {
                if (nnc < 0 || board[r][nnc] != comparison || visited[r][nnc])
                    return false;
                visited[r][nnc] = true;
            }
            return goRow(r, c + 1);
        } else return false;
    }

    static boolean goCol(int r, int c) {
        if (r >= N-1) return true;

        int nr = r + 1;
        if (board[r][c] == board[nr][c])
            return goCol(nr, c);
        else if (board[r][c] == board[nr][c] + 1) { // down
            int comparison = board[nr][c];
            for (int nnr = nr; nnr < nr + X; nnr++) {
                if (nnr >= N || board[nnr][c] != comparison || visited[nnr][c])
                    return false;
                visited[nnr][c] = true;
            }
            return goCol(r + X, c);
        } else if (board[r][c] + 1 == board[nr][c]) { // up
            int comparison = board[r][c];
            for (int nnr = r; nnr > r - X; nnr--) {
                if (nnr < 0 || board[nnr][c] != comparison || visited[nnr][c])
                    return false;
                visited[nnr][c] = true;
            }
            return goCol(r + 1, c);
        } else return false;
    }

    static int solution() {
        int cnt = 0;
        for (int r = 0; r < N; r++)
            cnt += goRow(r, 0) ? 1 : 0;

        for (int i = 0; i < N; i++)
            Arrays.fill(visited[i], false);

        for (int c = 0; c < N; c++)
            cnt += goCol(0, c) ? 1 : 0;

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }
}
