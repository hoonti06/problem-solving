package boj.p1XXX.p1103_게임;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] board;
    static int[][] memo;
    static boolean[][] visited;
//    static int[][] memo;
    static int go(int r, int c, int cnt) {
        if (visited[r][c])
            return -1;

        if (memo[r][c] > 0)
            return memo[r][c];

        visited[r][c] = true;
        memo[r][c] = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i] * board[r][c];
            int nc = c + dy[i] * board[r][c];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == -1)
                continue;

            int ret = go(nr, nc, cnt + 1);
            if (ret == -1) {
                memo[r][c] = -1;
                break;
            }

            memo[r][c] = Math.max(memo[r][c], ret + 1);
        }
        visited[r][c] = false;
        return memo[r][c];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        memo = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = in.readLine();
            for (int j = 0; j < M; j++) {
                char cur = line.charAt(j);
                board[i][j] = (cur == 'H' ? -1 : cur - '0');
            }
        }

        System.out.println(go(0, 0, 0));
//        System.out.println(solution());
    }
}
