package swea.p4317_칩생산;

import java.util.*;
import java.io.*;

public class Solution {
    static int N, M;
    static int[][] visited = new int[26][11];
    static int[][] board = new int[26][11];
    static int[][] memo = new int[25][(1 << 10) + 1]; // [row][rowState]
    static int res = 0;

    static int[] dx = {0, 0, 1, 1};
    static int[] dy = {0, 1, 1, 0};

    static void setSquare(int r, int c) {
        visited[r][c] = 2;
        for (int i = 1; i < 4; i++)
            visited[r+dx[i]][c+dy[i]] = 1;
    }

    static void clearSquare(int r, int c) {
        for (int i = 0; i < 4; i++)
            visited[r+dx[i]][c+dy[i]] = 0;
    }

    static boolean possible(int r, int c) {
        if (c >= M-1) return false;

        for (int i = 0; i < 4; i++)
            if (board[r+dx[i]][c+dy[i]] == 1) return false;
        for (int i = 0; i < 4; i++)
            if (visited[r+dx[i]][c+dy[i]] != 0) return false;

        return true;
    }

    static int getRowState(int r) {
        int rowState = 0;
        for (int j = 0; j < M-1; j++) {
            rowState <<= 1;
            if (visited[r][j] == 2) rowState |= 1;
        }
        return rowState;
    }

    static int go(int idx) {
        int r = idx / M;
        int c = idx % M;

        // 종료 조건
        if (r == N-2 && c == M-1) return 0;

        int rowState = 0;
        // col 끝에 도달하면 이미 방문한 rowState 상태인지 확인
        if (c == M-1) {
            rowState = getRowState(r);
            if (memo[r][rowState] != -1) return memo[r][rowState];
        }

        int max = 0;

        // 사각형 두기
        if (possible(r, c)) {
            setSquare(r, c);
            int ret = go(idx + 1);
            max = Math.max(max, ret + 1);
            clearSquare(r, c);
        }

        // 사각형 두지 않기
        int ret = go(idx+1);
        max = Math.max(max, ret);

        // memo하기
        if (c == M - 1)
            memo[r][rowState] = max;
        return max;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            for (int i = 0; i < N; i++)
                Arrays.fill(memo[i], -1);

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            for (int c = 0; c < M; c++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int r = 0; r < N; r++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            res = go(0);
            System.out.println("#" + tc + " " + res);
        }
    }
}
