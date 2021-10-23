package boj.p21609;

import java.util.*;
import java.io.*;

public class Main {
    static final int EMPTY = -9;
    static final int BLACK = -1;
    static final int RAINBOW = 0;

    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = { -1,  0, 1, 0};
    static int[] dy = {  0, -1, 0, 1};

    static int solution() {
        int res = 0;

        while (true) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            int maxCnt = 0;
            int maxRainbowCnt = 0;
            int maxR = -1;
            int maxC = -1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] || board[i][j] == EMPTY ||
                            board[i][j] == RAINBOW || board[i][j] == BLACK) {
                        continue;
                    }
                    int[] cnts = bfs(i, j);
                    if (maxCnt < cnts[0]) {
                        maxCnt = cnts[0];
                        maxRainbowCnt = cnts[1];
                        maxR = i;
                        maxC = j;
                    } else if (maxCnt == cnts[0]) {
                        if (maxRainbowCnt <= cnts[1]) {
                            maxRainbowCnt = cnts[1];
                            maxR = i;
                            maxC = j;
                        }
                    }
                }
            }
            if (maxCnt < 2) {
                break;
            }

            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }
            delGroup(maxR, maxC);
            res += maxCnt * maxCnt;

            doGravity();
            rotate90();
            doGravity();
        }
        return res;
    }

    static void delGroup(int sr, int sc) {
        int pivotColor = board[sr][sc];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(sr);
        q.offer(sc);
        visited[sr][sc] = true;
        board[sr][sc] = EMPTY;

        while (!q.isEmpty()) {
            int r = q.poll();
            int c = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                int color = board[nr][nc];
                if (visited[nr][nc] || color == BLACK || color == EMPTY
                        || (color != RAINBOW && color != pivotColor)) {
                    continue;
                }

                visited[nr][nc] = true;

                q.offer(nr);
                q.offer(nc);
                board[nr][nc] = EMPTY;
            }
        }
    }

    static int[] bfs(int sr, int sc) {
        int pivotColor = board[sr][sc];
        Queue<Integer> rainbowQ = new ArrayDeque<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(sr);
        q.offer(sc);
        visited[sr][sc] = true;

        int cnt = 1;
        int rainbowCnt = 0;
        while (!q.isEmpty()) {
            int r = q.poll();
            int c = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                int color = board[nr][nc];
                if (visited[nr][nc] || color == BLACK || color == EMPTY
                        || (color != RAINBOW && color != pivotColor)) {
                    continue;
                }

                visited[nr][nc] = true;

                q.offer(nr);
                q.offer(nc);
                cnt++;

                if (color == RAINBOW) {
                    rainbowCnt++;
                    rainbowQ.offer(nr);
                    rainbowQ.offer(nc);
                }
            }
        }

        while (!rainbowQ.isEmpty()) {
            int r = rainbowQ.poll();
            int c = rainbowQ.poll();
            visited[r][c] = false;
        }

        return new int[] {cnt, rainbowCnt};
    }

    static void doGravity() {
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                for (int k = N-1; k > 0; k--) {
                    if (board[k][j] == EMPTY && board[k-1][j] != BLACK) {
                        int tmp = board[k][j];
                        board[k][j] = board[k-1][j];
                        board[k-1][j] = tmp;
                    }
                }
            }
        }
    }

    static void rotate90() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[N-1-j][i] = board[i][j];
            }
        }
        board = tmp;
    }

    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = 1;
        for (int tc = 1; tc <= testcase; tc++) {

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int res = solution();
            System.out.println(res);
        }
    }
}

