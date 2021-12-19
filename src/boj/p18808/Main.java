package boj.p18808;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int res;
    static boolean[][] visited;
    static int[][][][] stickers;
    static int[] cnts;

    static void recur(int start, int sum) {
        if (start >= K) {
            res = Math.max(res, sum);
            return;
        }

        for (int idx = start; idx < K; idx++) {
            for (int d = 0; d < 4; d++) {
                int height = stickers[idx][d].length;
                int width = stickers[idx][d][0].length;
                for (int sr = 0; sr < N - height; sr++) {
                    for (int sc = 0; sc < M - width; sc++) {
                        boolean possible = true;
                        Queue<Integer> q = new ArrayDeque<>();
                        for (int r = sr, i = 0; r < sr + height && possible; r++, i++) {
                            for (int c = sc, j = 0; c < sc + width; c++, j++) {
                                if (stickers[idx][d][i][j] == 0) {
                                    continue;
                                }
                                if (visited[r][c]) {
                                    possible = false;
                                    break;
                                } else {
                                    visited[r][c] = true;
                                    q.offer(r);
                                    q.offer(c);
                                }
                            }
                        }

                        if (possible) {
                            recur(start + 1, sum + cnts[idx]);
                            while (!q.isEmpty()) {
                                int r = q.poll();
                                int c = q.poll();
                                visited[r][c] = false;
                            }
                        } else {
                            while (!q.isEmpty()) {
                                int r = q.poll();
                                int c = q.poll();
                                visited[r][c] = false;
                            }
                            recur(start + 1, sum);
                        }
                    }
                }
            }
        }
    }

    static int[][] rotate(int[][] board) {
        int height = board.length;
        int width = board[0].length;
        int[][] ret = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ret[j][height - 1 - i] = board[i][j];
            }
        }
        return ret;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        stickers = new int[K][4][][];
        cnts = new int[K];
        for (int idx = 0; idx < K; idx++) {
            st = new StringTokenizer(in.readLine(), " ");
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            stickers[idx][0] = new int[height][width];
            for (int r = 0; r < height; r++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int c = 0; c < width; c++) {
                    stickers[idx][0][r][c] = Integer.parseInt(st.nextToken());
                    if (stickers[idx][0][r][c] == 1) {
                        cnts[idx]++;
                    }
                }
            }
            for (int d = 0; d < 3; d++) {
                stickers[idx][d+1] = rotate(stickers[idx][d]);
            }
        }
        recur(0, 0);
        System.out.println(res);
    }
}
