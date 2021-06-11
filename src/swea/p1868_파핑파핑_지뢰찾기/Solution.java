package swea.p1868_파핑파핑_지뢰찾기;

import java.io.*;
import java.util.*;

public class Solution {
    static final char MINE = '*';
//    static final char MINE = '*';
    static int N;
    static char[][] board;
    static int[][] cnt;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0, -1, 1, -1,  1};
    static int[] dy = { 0,-1, 0, 1, -1, 1,  1, -1};

    static Queue<Integer> zeroq;

    static boolean inBoundary(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N)
            return false;
        return true;
    }

    static void countMine() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == MINE) {
                    cnt[i][j] = -1;
                    continue;
                }
                for (int k = 0; k < 8; k++) {
                    int nr = i + dx[k];
                    int nc = j + dy[k];
                    if (inBoundary(nr, nc) && board[nr][nc] == MINE)
                        cnt[i][j]++;
                }
                if (cnt[i][j] == 0) {
                    zeroq.offer(i); zeroq.offer(j);
                }
            }
        }
    }

    static void bfs(int sr, int sc) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(sr); q.offer(sc);
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int r = q.poll();
            int c = q.poll();
            for (int i = 0; i < 8; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                if (!inBoundary(nr, nc) || visited[nr][nc] || board[nr][nc] == MINE)
                    continue;

                visited[nr][nc] = true;
                if (cnt[nr][nc] == 0) {
                    q.offer(nr); q.offer(nc);
                }
            }
        }
    }

    static int solution() {
        countMine();

        int res = 0;
        while (!zeroq.isEmpty()) {
            int r = zeroq.poll();
            int c = zeroq.poll();
            if (visited[r][c]) continue;
            bfs(r, c);
            res++;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == MINE) continue;
                if (!visited[i][j]) res++;
            }
        }
        return res;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            cnt = new int[N][N];
            visited = new boolean[N][N];
            zeroq = new ArrayDeque<>();
            board = new char[N][];
            for (int i = 0; i < N; i++)
                board[i] = in.readLine().toCharArray();

            System.out.println("#" + tc + " " + solution());
//            break;
        }
    }
}
