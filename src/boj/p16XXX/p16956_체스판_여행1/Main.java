package boj.p16XXX.p16956_체스판_여행1;

import java.io.*;
import java.util.*;

public class Main {
    static final int KNIGHT = 0;
    static final int BISHOP = 1;
    static final int ROOK = 2;
    static int N;
    static int[][] board;
    static boolean[][][][] visited;
    static int[] kx = { -1, 1,  2, 2, -1,  1, -2, -2};
    static int[] ky = {  2, 2, -1, 1, -2, -2, -1,  1};
    static int[] bx = { -1, 1, 1,-1};
    static int[] by = { -1,-1, 1, 1};
    static int[] rx = { -1, 0, 1, 0};
    static int[] ry = {  0, 1, 0,-1};

    static HashMap<Integer, Point> map = new HashMap<>();

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r; this.c = c;
        }
    }

    static class Node implements Comparable<Node> {
        int r, c, piece, cnt;
        Node(int r, int c, int piece, int cnt) {
            this.r = r; this.c = c;
            this.piece = piece;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.c, o.cnt);
        }
    }
    static boolean possible(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N)
            return false;
        return true;
    }


    static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < 3; i++)
            pq.offer(new Node(map.get(1).r, map.get(1).c, i, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int r = cur.r, c = cur.c;
            int piece = cur.piece, cnt = cur.cnt;

            if (board[r][c] == N*N) return cnt;
            if (visited[r][c][piece][cnt]) continue;

            visited[r][c][piece][cnt] = true;

            boolean canMove = false;

            if (piece == KNIGHT) {
                for (int i = 0; i < 8; i++) {
                    int nr = r + kx[i];
                    int nc = c + ky[i];
                    int nCnt = cnt + 1;
                    if (!possible(nr, nc)) continue;

                    if (board[nr][nc] != board[r][c] + 1)
                        continue;

                    if (visited[nr][nc][piece][nCnt])
                        continue;

                    canMove = true;
                    pq.offer(new Node(nr, nc, piece, nCnt));
                }
            } else if (piece == BISHOP) {
                for (int i = 0; i < 4; i++) {
                    int nr = r, nc = c;
                    int nCnt = cnt + 1;
                    while (true) {
                        nr += bx[i];
                        nc += by[i];
                        if (!possible(nr, nc)) break;

                        if (board[nr][nc] != board[r][c] + 1)
                            continue;

                        if (visited[nr][nc][piece][nCnt])
                            continue;

                        pq.offer(new Node(nr, nc, piece, nCnt));
                    }
                }

            } else {
                for (int i = 0; i < 4; i++) {
                    int nr = r, nc = c;
                    int nCnt = cnt + 1;
                    while (true) {
                        nr += rx[i];
                        nc += ry[i];
                        if (!possible(nr, nc)) break;

                        if (board[nr][nc] != board[r][c] + 1)
                            continue;
                        if (visited[nr][nc][piece][nCnt])
                            continue;

                        canMove = true;
                        pq.offer(new Node(nr, nc, piece, nCnt));
                    }
                }
            }
            if (!canMove) {
                int nCnt = cnt + 2 + (piece == KNIGHT? 1 : 0);
                int nr = map.get(cnt+1).r;
                int nc = map.get(cnt+1).c;

            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        board = new int[N][N];
        visited = new boolean[N][N][3][N*N*3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                map.put(board[i][j], new Point(i, j));
            }
        }
        System.out.println(bfs());
    }
}
