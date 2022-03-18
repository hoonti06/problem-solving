package programmers.p68645;

import java.util.*;

public class Solution {
    static int N;
    static int[][] board;
    static int[] dx = { 1, 0, -1};
    static int[] dy = {-1, 1, -1};

    public int[] solution(int n) {
        N = n * 2 - 1;
        board = new int[N][N];

        int r = n - 1;
        int c = n - 1;
        board[r][c] = 1;
        int dir = 0;
        int cnt = 2;

        int max = n * (n + 1) / 2;
        while (cnt <= max) {
            int curDx = dir == 1? dx[dir] * 2 : dx[dir];
            int durDy = dir == 1? dy[dir] * 2 : dy[dir];

            int nr = r + curDx;
            int nc = c + durDy;
            if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] > 0) {
                dir = (dir + 1) % 3;
                continue;
            }
            board[nr][nc] = cnt++;

            r = nr;
            c = nc;
        }

        cnt = 0;
        int[] answer = new int[max];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                answer[cnt++] = board[i][j];
            }
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(4);
        s.solution(5);
        s.solution(6);
    }
}
