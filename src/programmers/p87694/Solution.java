package programmers.p87694;

public class Solution {
    static int N;
    static int[][] board;
    static final int DOT = 1;
    static final int ITEM = 2;
    static final int USER = 3;
    static final int VISITED = 4;
    // <- v -> ^
    static int[] dx = {  0, 1, 0, -1};
    static int[] dy = { -1, 0, 1,  0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        board = new int[51][51];
        N = rectangle.length;
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        for (int[] rec : rectangle) {
            int r1 = rec[0] * 2;
            int c1 = rec[1] * 2;
            if (minR > r1) {
                minR = r1;
                minC = c1;
            } else if (minR == r1) {
                if (minC > c1) {
                    minC = c1;
                }
            }

            int r2 = rec[2] * 2;
            int c2 = rec[3] * 2;
            if (minR > r2) {
                minR = r2;
                minC = c2;
            } else if (minR == r2) {
                if (minC > c2) {
                    minC = c2;
                }
            }

            for (int r = r1; r <= r2; r++) {
                board[r][c1] = board[r][c1] = DOT;
                board[r][c2] = board[r][c2] = DOT;
            }
            for (int c = c1; c <= c2; c++) {
                board[r1][c] = board[r1][c] = DOT;
                board[r2][c] = board[r2][c] = DOT;
            }
        }
        board[characterX*2][characterY*2] = USER;
        board[itemX*2][itemY*2] = ITEM;

        int r = minR;
        int c = minC + 1;
        int dir = 0;
        int start = -1;
        int end = -1;

        int cnt = 0;
        while (true) {
            int nr = r + dx[dir];
            int nc = c + dy[dir];

            if (board[nr][nc] == 0) {
                dir = (dir + 1) % 4;
                continue;
            } else if (board[nr][nc] == USER || board[nr][nc] == ITEM) {
                if (start == -1) {
                    start = cnt;
                } else {
                    end = cnt;
                }
            } else if (board[nr][nc] == VISITED) {
                break;
            }
            board[nr][nc] = VISITED;
            r = nr;
            c = nc;
            dir = (dir + 3) % 4;
            cnt++;
        }

        int dist = end - start;
        return Math.min(cnt - dist, dist) / 2;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[][]{{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}}, 1, 3, 7, 8);
        s.solution(new int[][]{{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}}, 9, 7, 6, 1);
        s.solution(new int[][]{{1,1,5,7}}, 1, 1, 4, 7);
        s.solution(new int[][]{{2,1,7,5},{6,4,10,10}}, 3, 1, 7, 10);
        s.solution(new int[][]{{2,2,5,5},{1,3,6,4},{3,1,4,6}}, 1, 4, 6, 3);
    }
}
