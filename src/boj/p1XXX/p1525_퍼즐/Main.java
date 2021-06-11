package boj.p1XXX.p1525_퍼즐;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int r, c;
        int[][] board;

        Node(int r, int c, int[][] board) {
            this.r = r;
            this.c = c;
            this.board = new int[3][3];
            copyBoard(board, this.board);
        }
    }

    static void copyBoard(int[][] src, int[][] dest) {
        for (int i = 0; i < 3; i++)
            System.arraycopy(src[i], 0, dest[i], 0, 3);
    }

    static void swap(int[][] arr, int sr, int sc, int dr, int dc) {
        int tmp = arr[sr][sc];
        arr[sr][sc] = arr[dr][dc];
        arr[dr][dc] = tmp;
    }

    static Set<String> visited = new HashSet<>();

    static int[][] input = new int[3][3];
    static int sr, sc;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};


    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(sr, sc, input));
        int cnt = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int qs = 0; qs < qSize; qs++) {
                Node cur = q.poll();

                boolean isFin = true;
                for (int i = 0; i < 8; i++)
                    if (cur.board[i / 3][i % 3] != i + 1)
                        isFin = false;

                if (isFin) return cnt;

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dx[i];
                    int nc = cur.c + dy[i];

                    if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3)
                        continue;

                    int[][] nextBoard = new int[3][3];
                    copyBoard(cur.board, nextBoard);
                    swap(nextBoard, cur.r, cur.c, nr, nc);

                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < 9; j++)
                        sb.append(nextBoard[j / 3][j % 3]);

                    if (visited.contains(sb.toString()))
                        continue;

                    visited.add(sb.toString());
                    q.offer(new Node(nr, nc, nextBoard));
                }
            }
            cnt++;
        }
        return -1;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if (input[i][j] == 0) {
                    sr = i;
                    sc = j;
                }
            }
        }
        System.out.println(bfs());
    }
}
