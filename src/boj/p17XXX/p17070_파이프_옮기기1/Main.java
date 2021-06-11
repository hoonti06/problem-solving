package boj.p17XXX.p17070_파이프_옮기기1;

import java.io.*;
import java.util.*;

public class Main {
    static final int WALL = 1;
    static int N;
    static int[][] board;

    static final int VERTICAL = 0;
    static final int HORIZONTAL = 1;
    static final int DIAGONAL = 2;


    static class Node {
        int r, c, dir;
        Node (int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }


    static int bfs() {
        int res = 0;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(1, 2, HORIZONTAL));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.r == N && cur.c == N) {
                res++;
                continue;
            }

            int nr = 0;
            int nc = 0;
            int sum = 0;
            if (cur.dir == HORIZONTAL || cur.dir == DIAGONAL) {
                nr = cur.r;
                nc = cur.c + 1;
                if (0 < nr && nr <= N && 0 < nc && nc <= N && board[nr][nc] != WALL)
                    q.offer(new Node(nr, nc, HORIZONTAL));
            }
            if (cur.dir == VERTICAL || cur.dir == DIAGONAL) {
                nr = cur.r + 1;
                nc = cur.c;
                if (0 < nr && nr <= N && 0 < nc && nc <= N && board[nr][nc] != WALL)
                    q.offer(new Node(nr, nc, VERTICAL));
            }
            nr = cur.r + 1;
            nc = cur.c + 1;
            if (0 < nr && nr <= N && 0 < nc && nc <= N
                && board[nr][nc] != WALL && board[nr-1][nc] != WALL && board[nr][nc-1] != WALL)
                q.offer(new Node(nr, nc, DIAGONAL));
        }
        return res;
    }

    static int[][][] memo;
    static int go(int r, int c, int s) {
//        if (r == 1 && c == 2)
//            return s != VERTICAL? 1 : 0;

        if (r < 1 || r > N || c < 1 || c > N)
            return 0;
        if (board[r][c] == WALL) return 0;

        if (memo[r][c][s] != -1) return memo[r][c][s];
        memo[r][c][s] = 0;

        if (s == VERTICAL) {
            if (r-1 >= 1 && board[r-1][c] != WALL) {
                memo[r][c][s] += go(r-1, c, VERTICAL);
                memo[r][c][s] += go(r-1, c, DIAGONAL);
            }
        } else if (s == HORIZONTAL) {
            if (c-1 >= 1 && board[r][c-1] != WALL) {
                memo[r][c][s] += go(r, c-1, HORIZONTAL);
                memo[r][c][s] += go(r, c-1, DIAGONAL);
            }
        } else {
            if (r-1 >=1 && c-1 >=1 && board[r-1][c] != WALL && board[r][c-1] != WALL) {
                memo[r][c][s] += go(r-1, c-1, VERTICAL);
                memo[r][c][s] += go(r-1, c-1, HORIZONTAL);
                memo[r][c][s] += go(r-1, c-1, DIAGONAL);
            }
        }
        return memo[r][c][s];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        board = new int[N+1][N+1];
        memo = new int[N+1][N+1][3];
        for (int[][] d2 : memo)
            for (int[] d1 : d2) Arrays.fill(d1, -1);

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 1; j <= N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        memo[1][2][HORIZONTAL] = 1;

//        System.out.println(bfs());

        int res = 0;
        for (int s = 0; s < 3; s++) res += go(N, N, s);
        System.out.println(res);
    }
}
