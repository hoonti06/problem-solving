package boj.p14XXX.p14391;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, res;
    static int[][] board;
    static boolean[][] visited;

    static void go(int pos, int sum) {
        if (pos >= N * M) {
            res = Math.max(res, sum);
            return;
        }

        int r = pos / M;
        int c = pos % M;

        if (visited[r][c]) {
            go(pos + 1, sum);
            return;
        }

        // 가로로 1, 2, ..., M 탐색
        int num = 0;
        int nc = c;
        for (; nc < M; nc++) {
            if (visited[r][nc]) break;
            visited[r][nc] = true;
            num *= 10;
            num += board[r][nc];
            go(r * M + nc + 1, sum + num);
        }
        // 방문 여부 초기화
        for (nc--; nc >= c; nc--) {
            visited[r][nc] = false;
        }

        // 세로로 1, 2, ..., N 탐색
        visited[r][c] = true; // 1x1은 가로로 이미 탐색했으니 세로일 때는 skip
        num = board[r][c];
        int nr = r + 1;
        for (; nr < N; nr++) {
            if (visited[nr][c]) break;
            visited[nr][c] = true;
            num *= 10;
            num += board[nr][c];
            go(pos + 1, sum + num);
        }
        // 방문 여부 초기화
        for (nr--; nr >= r; nr--) {
            visited[nr][c] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        res = 0;
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            char[] line = in.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = line[j] - '0';
            }
        }

        go(0, 0);
        System.out.println(res);
    }
}
