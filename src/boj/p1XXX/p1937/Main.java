package boj.p1XXX.p1937;

import java.util.*;

public class Main {
    static int N;
    static final int MAX_N = 510;
    static int[][] board = new int[MAX_N][MAX_N];
    static int[][] cnt = new int[MAX_N][MAX_N];
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};

    static int res;

    static class Node {
        int r, c, w;

        Node(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }

    static int dfs(int r, int c) {
        if (cnt[r][c] != -1)
            return cnt[r][c];

        cnt[r][c] = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;

            if (board[nr][nc] <= board[r][c])
                continue;

            cnt[r][c] = Math.max(cnt[r][c], dfs(nr, nc) + 1);
        }
        return cnt[r][c];
    }

    static void findMax() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res = Math.max(res, cnt[i][j]);
//                System.out.print(cnt[i][j] + " ");
            }
//            System.out.println();
        }
    }

    static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cnt[i][j] == -1)
                    dfs(i, j);
            }
        }
        findMax();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        res = 1;
        for (int i = 0; i < MAX_N; i++) {
            Arrays.fill(cnt[i], -1);
        }
        N = sc.nextInt();


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
//                sorted.add(new Node(i, j, board[i][j]));
            }
        }
//        sorted.sort((o1, o2) -> o1.w - o2.w);

        solution();
        System.out.println(res);
    }
}
