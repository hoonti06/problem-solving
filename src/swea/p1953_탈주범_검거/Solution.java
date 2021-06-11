package swea.p1953_탈주범_검거;

import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, L;
    static int[] dx = {-1, 0, 1,  0};
    static int[] dy = { 0, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;
    static int[][] dirs = new int[8][];
    static {
        dirs[1] = new int[]{0, 1, 2, 3}; dirs[2] = new int[]{0, 2};
        dirs[3] = new int[]{1, 3}; dirs[4] = new int[]{0, 1};
        dirs[5] = new int[]{1, 2}; dirs[6] = new int[]{2, 3};
        dirs[7] = new int[]{3, 0};
    }

    static int bfs(int sr, int sc) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(sr); q.offer(sc);
        visited[sr][sc] = true;

        int cnt = 1, res = 1;
        while (!q.isEmpty() && cnt < L) {
            for (int qs = 0, qSize = q.size()/2; qs < qSize; qs++) {
                int r = q.poll();
                int c = q.poll();
                int type = board[r][c];
                for (int dir : dirs[type]) {
                    int nr = r + dx[dir];
                    int nc = c + dy[dir];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M
                            || board[nr][nc] == 0 || visited[nr][nc])
                        continue;

                    int ntype = board[nr][nc];
                    for (int ndir : dirs[ntype]) {
                        if (ndir != (dir + 2) % 4) continue;

                        visited[nr][nc] = true;
                        res++;
                        q.offer(nr); q.offer(nc);
                        break;
                    }
                }
            }
            cnt++;
        }
        return res;
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print((visited[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws  Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            board = new int[N][M];
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < M; j++)
                    board[i][j] = Integer.parseInt(st.nextToken());
            }
            System.out.println("#" + tc + " " + bfs(sr, sc));
        }
    }
}
