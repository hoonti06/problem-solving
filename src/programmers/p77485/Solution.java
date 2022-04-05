package programmers.p77485;

import java.io.*;
import java.util.*;

public class Solution {
    static int[][] board;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1,  0, -1};
    public int[] solution(int rows, int columns, int[][] queries) {
        board = new int[rows][columns];

        int cnt = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = cnt++;
            }
        }

        int[] res = new int[queries.length];
        System.out.println();
        for (int i = 0; i < queries.length; i++) {
            int sr = queries[i][0] - 1;
            int sc = queries[i][1] - 1;
            int er = queries[i][2] - 1;
            int ec = queries[i][3] - 1;
            int ret = rotate(sr, sc, er, ec);
            res[i] = ret;
        }

        System.out.println(Arrays.toString(res));
        return res;
    }

    public int rotate(int sr, int sc, int er, int ec) {
        int minVal = board[sr][sc];
        int start = board[sr][sc];
        int dir = 0;
        int r = sr;
        int c = sc;
        while (true) {
            int nr = r + dx[dir];
            int nc = c + dy[dir];
            if (nr == sr && nc == sc) {
                board[r][c] = start;
                break;
            }

            if (nr < sr || nr > er || nc < sc || nc > ec) {
                dir = (dir + 1) % 4;
                continue;
            }

            board[r][c] = board[nr][nc];
            minVal = Math.min(minVal, board[r][c]);
            printBoard();
            r = nr;
            c = nc;

        }
        printBoard();
        return minVal;
    }

    public void printBoard() {
        boolean debug = false;
        if (!debug) return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf("%3d", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(6, 6, new int[][]{{2,2,5,4},{3,3,6,6},{5,1,6,3}});
        s.solution(3, 3, new int[][]{{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}});
        s.solution(100, 97, new int[][]{{1,1,100,97}});
    }
}
