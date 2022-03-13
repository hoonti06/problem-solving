package programmers.p81302;

import java.util.*;

public class Solution {
    static char[][][] boards;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = {  0,-1, 0, 1 };
    public int[] solution(String[][] places) {
        boards = new char[5][5][5];
        int[] res = new int[5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boards[i][j] = places[i][j].toCharArray();
            }
            res[i] = isValid(boards[i])? 1 : 0;
        }
        return res;
    }
    public boolean isValid(char[][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] != 'P') {
                    continue;
                }

                if (!bfs(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean bfs(char[][] board, int sr, int sc) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];
        q.offer(sr);
        q.offer(sc);
        visited[sr][sc] = true;

        int cnt = 0;
        while (!q.isEmpty() && cnt < 2) {
            for (int qs = 0, qSize = q.size()/2; qs < qSize; qs++) {
                int r = q.poll();
                int c = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];

                    if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) {
                        continue;
                    }
                    if (visited[nr][nc] || board[nr][nc] == 'X') {
                        continue;
                    }
                    if (board[nr][nc] == 'P') {
                        return false;
                    }

                    visited[nr][nc] = true;
                    q.offer(nr);
                    q.offer(nc);
                }
            }
            cnt++;
        }
        return true;
    }


    public static void main(String[] args) {
        String[][] places = new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(places)));
    }
}
