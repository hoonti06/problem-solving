package boj.p3XXX.p3108_로고;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_N = 1010;
    static int N;
    static boolean[][] board = new boolean[MAX_N][MAX_N];
    static boolean[][][] dir = new boolean[MAX_N][MAX_N][4];
    static boolean[][] visited = new boolean[MAX_N][MAX_N];
    // ^ < v >자
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static final int UP = 0;
    static final int LEFT = 1;
    static final int DOWN = 2;
    static final int RIGHT = 3;

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    static void paint(int x1, int y1, int x2, int y2) {
        int minx = Math.min(x1, x2);
        int maxx = Math.max(x1, x2);

        int miny = Math.min(y1, y2);
        int maxy = Math.max(y1, y2);

        board[minx][miny] = true;
        dir[minx][miny][UP] = dir[minx][miny][LEFT] = true;

        board[minx][maxy] = true;
        dir[minx][maxy][UP] = dir[minx][maxy][RIGHT] = true;

        board[maxx][miny] = true;
        dir[maxx][miny][LEFT] = dir[maxx][miny][DOWN] = true;

        board[maxx][maxy] = true;
        dir[maxx][maxy][DOWN] = dir[maxx][maxy][RIGHT] = true;

        for (int i = minx + 1; i < maxx; i++) {
            board[i][miny] = board[i][maxy] = true;
            dir[i][miny][DOWN] = dir[i][miny][UP] = true;
            dir[i][maxy][DOWN] = dir[i][maxy][UP] = true;
        }

        for (int i = miny + 1; i < maxy; i++) {
            board[minx][i] = board[maxx][i] = true;
            dir[minx][i][LEFT] = dir[minx][i][RIGHT] = true;
            dir[maxx][i][LEFT] = dir[maxx][i][RIGHT] = true;
        }
//        for (int i = 500; i < 510; i++) {
//            for (int j = 500; j < 510; j++) {
//                int sum = 0;
//                for (int k = 0; k < 4; k++)
//                    if(dir[i][j][k]) sum++;
//
//                System.out.print(sum + " ");
//            }
//            System.out.println();
//        }

    }

    static void bfs(int sr, int sc) {
        Queue<Node> q = new ArrayDeque<>();

        q.offer(new Node(sr, sc));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dx[i];
                int nc = cur.c + dy[i];

                if (nr < 0 || nr >= MAX_N || nc < 0 || nc >= MAX_N || !dir[nr][nc][i])
                    continue;

                if (!board[nr][nc] || visited[nr][nc])
                    continue;

                visited[nr][nc] = true;
                q.offer(new Node(nr, nc));
            }
        }
    }

    static int solution() {
        int cnt = -1;
        for (int i = 0; i < MAX_N; i++) {
            for (int j = 0; j < MAX_N; j++) {
                if (board[i][j] && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
//                    for (int k = 500; k < 510; k++) {
//                        for (int l = 500; l < 510; l++) {
//                            System.out.print((visited[k][l]? 1 : 0) + " ");
//                        }
//                        System.out.println();
//                    }
//                    System.out.println();
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        board[500][500] = true;
        dir[500][500][UP] = dir[500][500][DOWN] = true;
        dir[500][500][LEFT] = dir[500][500][RIGHT] = true;

        N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken()) + 500;
            int y1 = Integer.parseInt(st.nextToken()) + 500;
            int x2 = Integer.parseInt(st.nextToken()) + 500;
            int y2 = Integer.parseInt(st.nextToken()) + 500;

            paint(x1, y1, x2, y2);
        }

        System.out.println(solution());

//        for (int i = 500; i < 510; i++) {
//            for (int j = 500; j < 510; j++) {
//                int sum = 0;
//                for (int k = 0; k < 4; k++)
//                    if(dir[i][j][k]) sum++;
//
//                System.out.print(sum + " ");
//            }
//            System.out.println();
//        }
    }
}
