package boj.p2XXX.p2239_스도쿠;

import java.io.*;
import java.util.*;

public class Main {
    static int N = 9, M;
    static int[][] board = new int[N][N];

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r; this.c = c;
        }
    }
    static List<Point> list = new ArrayList<>();
    static Set<Integer>[] rowSet = new HashSet[N];
    static Set<Integer>[] colSet = new HashSet[N];
    static Set<Integer>[] subSet = new HashSet[N];
    static {
        for (int i = 0; i < N; i++) {
            rowSet[i] = new HashSet<>();
            colSet[i] = new HashSet<>();
            subSet[i] = new HashSet<>();
        }
    }

    static boolean isValid(int r, int c) {
        int comparison = board[r][c];
        if (rowSet[r].contains(comparison)) return false;
        if (colSet[c].contains(comparison)) return false;
        if (subSet[(r/3)*3+(c/3)].contains(comparison))
            return false;
        return true;
    }

    static boolean dfs(int cnt) {
        if (cnt >= M) return true;

        int r = list.get(cnt).r;
        int c = list.get(cnt).c;
        for (int i = 1; i < 10; i++) {
            board[r][c] = i;
            if (!isValid(r, c)) continue;

            int subidx = (r/3) * 3 + (c/3);
            rowSet[r].add(i);
            colSet[c].add(i);
            subSet[subidx].add(i);

            if (dfs(cnt+1)) return true;

            rowSet[r].remove(i);
            colSet[c].remove(i);
            subSet[subidx].remove(i);
        }
        board[r][c] = 0;
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            String line = in.readLine();
            int subi = i/3*3;
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j)-'0';
                if (board[i][j] == 0) list.add(new Point(i, j));
                else {
                    rowSet[i].add(board[i][j]);
                    colSet[j].add(board[i][j]);
                    subSet[subi+j/3].add(board[i][j]);
                }
            }
        }
        M = list.size();
        dfs(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                sb.append(board[i][j]);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
