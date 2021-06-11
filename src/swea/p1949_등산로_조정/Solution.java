package swea.p1949_등산로_조정;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int N, K;
    static int[][] board;
    static int res;
    static int max;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0,-1, 0, 1};
    static boolean[][] visited;
    static void dfs(int r, int c, int cnt, boolean cut) {
        res = Math.max(res, cnt);
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;

            if (visited[nr][nc]) continue;

            if (board[nr][nc] >= board[r][c]) {
                if (cut) continue;

                if (board[nr][nc] - board[r][c] < K) {
                    visited[r][c] = true;
                    int backup = board[nr][nc];
                    board[nr][nc] = board[r][c] - 1;
                    dfs(nr, nc, cnt + 1, true);
                    board[nr][nc] = backup;
                    visited[r][c] = false;
                }
            } else {
                visited[r][c] = true;
                dfs(nr, nc, cnt+1, cut);
                visited[r][c] = false;
            }
        }
    }

    static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == max) {
                    visited[i][j] = true;
                    dfs(i, j, 1, false);
                    visited[i][j] = false;
                }
            }
        }

    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            res = Integer.MIN_VALUE;
            max = Integer.MIN_VALUE;

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, board[i][j]);
                }
            }
            solution();
            System.out.println("#" + tc + " " + res);
        }
    }
}
