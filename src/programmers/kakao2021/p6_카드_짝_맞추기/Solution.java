package programmers.kakao2021.p6_카드_짝_맞추기;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0,-1, 0, 1};
    static List<Point>[] list = new ArrayList[7];
    static {
        for (int i = 0; i < 7; i++)
            list[i] = new ArrayList<>();
    }

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static boolean inBoundary(int r, int c) {
        if (r < 0 || r >= 4 || c < 0 || c >= 4)
            return false;
        return true;
    }

    static boolean[] aa = new boolean[7];
    static int[] order = new int[3];

    static int bfs(Point[] aa) {
        int cnt = 0;
        for (int k = 0; k < 2; k++) {
            boolean[][] visited = new boolean[4][4];
            Point start = aa[k];
            Point end = aa[k+1];

            Queue<Point> q = new ArrayDeque<>();
            q.offer(start);
            visited[start.r][start.c] = true;

            while (!q.isEmpty()) {
                boolean isFin = false;
                for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                    Point cur = q.poll();
                    if (cur.r == end.r && cur.c == end.c) {
                        isFin = true;
                        curBoard[cur.r][cur.c] = 0;
                        break;
                    }

                    for (int i = 0; i < 4; i++) {
                        // arrow
                        int nr = cur.r + dx[i];
                        int nc = cur.c + dy[i];
                        if (!inBoundary(nr, nc)) continue;

                        if (!visited[nr][nc]) {
                            visited[nr][nc] = true;
                            q.offer(new Point(nr, nc));
                        }

                        if (curBoard[nr][nc] > 0) continue;
                        int r = nr, c = nc;
                        while (true) {
                            nr = r + dx[i];
                            nc = c + dy[i];

                            if (!inBoundary(nr, nc)) break;
                            r = nr;
                            c = nc;

                            if (curBoard[r][c] > 0) break;
                        }

                        if (!visited[r][c]) {
                            visited[r][c] = true;
                            q.offer(new Point(r, c));
                        }
                    }
                }
                if (isFin) break;
                cnt++;

            }
        }
        cr = aa[2].r; cc = aa[2].c;
        return cnt;
    }

    static int[][] boardBackup = new int[4][];
    static int cr, cc;
    static int ssr, ssc;
    static int[][] curBoard;
    static void init() {
        for (int i= 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                curBoard[i][j] = boardBackup[i][j];
        cr = ssr; cc = ssc;
    }

    static int curSum;
    static int res;
    static int N;
    static void combi(int cnt) {
        if (cnt >= N) {
//            init();
            res = Math.min(res, curSum);
            return;
        }
        int[][] backupBoard = new int[4][];
        for (int j = 0; j < 4; j++)
            backupBoard[j] = Arrays.copyOf(curBoard[j], 4);

        int backupr = cr, backupc = cc;
        for (int i = 1; i < 7; i++) {
            if (list[i].size() <= 0 || aa[i]) continue;
            aa[i] = true;
            order[cnt] = i;
            Point[] points = new Point[]{new Point(cr, cc), list[i].get(0), list[i].get(1)};
            for (int j = 0; j < 4; j++)
                curBoard[j] = Arrays.copyOf(backupBoard[j], 4);

            int ret = bfs(points);
            curSum += ret;
            combi(cnt+1);
            for (int j = 0; j < 4; j++)
                curBoard[j] = Arrays.copyOf(backupBoard[j], 4);
            curSum -= ret;
            cr = backupr;
            cc = backupc;
            points[1] = list[i].get(1); points[2] = list[i].get(0);
            ret = bfs(points);
            curSum += ret;
            combi(cnt+1);
            curSum -= ret;
            aa[i] = false;

            cr = backupr;
            cc = backupc;
            for (int j = 0; j < 4; j++)
                curBoard[j] = Arrays.copyOf(backupBoard[j], 4);
        }
    }

    public int solution(int[][] board, int sr, int sc) {
        curSum = 6;
        res = Integer.MAX_VALUE;
        list = new ArrayList[7];
        for (int i = 0; i < 7; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            boardBackup[i] = Arrays.copyOf(board[i], 4);
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > 0)
                    list[board[i][j]].add(new Point(i, j));
            }
        }
        N = 0;
        for (int i = 1; i < 7; i++) if (list[i].size() > 0) N++;
        cr = sr; cc = sc;
        curBoard = board;
        ssr = sr; ssc = sc;

        combi(0);

        int answer = res;
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] board2 = {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}};
        s.solution(board2, 0, 1);
        // 16

        int[][] board = {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
        s.solution(board, 1, 0);
        // 14


    }
}
