package boj.p21611;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static int[] dx = {-1, 1,  0, 0};
    static int[] dy = { 0, 0, -1, 1};
    static int[] cnts = new int[4];

    static int[] ddx = { 0, 1,  0, -1};
    static int[] ddy = {-1, 0,  1,  0};

    static Queue<Integer> q;
    static ArrayDeque<Group> groupStack;

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Group {
        int num, cnt;
        Group(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    static void solution(int d, int s) {
        deleteStraight(d, s);

        q = new ArrayDeque<>();
        insertIntoQ();

        groupStack = new ArrayDeque<>();

        Queue<Integer> otherQ = new ArrayDeque<>();
        int iterCnt = 0;
        while (true) {
            int curSum = 0;
            if (iterCnt > 0) {
                q = otherQ;
                otherQ = new ArrayDeque<>();
            }

            int sameCnt = 0;
            int prevNum = 0;
            if (!q.isEmpty()) {
                prevNum = q.poll();
                sameCnt = 1;
            }
            int curNum = 0;
            while (!q.isEmpty()) {
                curNum = q.poll();
                if (prevNum != curNum) {
                    if (sameCnt < 4) {
                        for (int i = 0; i < sameCnt; i++) {
                            otherQ.offer(prevNum);
                        }
                    } else {
                        cnts[prevNum] += sameCnt;
                        curSum++;
                    }
                    prevNum = curNum;
                    sameCnt = 1;
                } else {
                    sameCnt++;
                }
            }
            prevNum = curNum;
            if (sameCnt < 4) {
                for (int i = 0; i < sameCnt; i++) {
                    otherQ.offer(prevNum);
                }
            } else {
                cnts[prevNum] += sameCnt;
                curSum++;
            }

            iterCnt++;
            if (curSum == 0) {
                break;
            }
        }

        q.clear();
        int sameCnt = 0;
        int prevNum = 0;
        if (!otherQ.isEmpty()) {
            prevNum = otherQ.poll();
            sameCnt = 1;
        }
        int curNum = 0;
        while (!otherQ.isEmpty()) {
            curNum = otherQ.poll();
            if (prevNum != curNum) {
                q.offer(sameCnt);
                q.offer(prevNum);

                prevNum = curNum;
                sameCnt = 1;
            } else {
                sameCnt++;
            }
        }

        prevNum = curNum;
        if (sameCnt > 0) {
            q.offer(sameCnt);
            q.offer(prevNum);
        }

        clearBoard();
        setBoard(q);
    }

    static void deleteStraight(int d, int s) {
        int nr = N/2;
        int nc = N/2;
        for (int i = 0; i < s; i++) {
            nr += dx[d];
            nc += dy[d];
            board[nr][nc] = 0;
        }
    }

    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void insertIntoQ() {
        int r = N/2;
        int c = N/2;
        int d = 0;
        int cnt = 0;
        int maxCnt = 1;
        int dirCnt = 0;
        for (int i = 0; i < N*N-1; i++) {
            r += ddx[d];
            c += ddy[d];
            if (board[r][c] != 0) {
                q.offer(board[r][c]);
            }

            if (++cnt >= maxCnt) {
                dirCnt++;
                d = (d + 1) % 4;
                if (dirCnt % 2 == 0) {
                    maxCnt++;
                }
                cnt = 0;
            }
        }

    }

    static void setBoard(Queue<Integer> q) {
        int r = N/2;
        int c = N/2;
        int d = 0;
        int cnt = 0;
        int maxCnt = 1;
        int dirCnt = 0;
//        Queue<Point> q = new ArrayDeque<>();

        int i = 0;
        while (!q.isEmpty() && i < N*N-1) {
            r += ddx[d];
            c += ddy[d];
            board[r][c] = q.poll();

            if (++cnt >= maxCnt) {
                dirCnt++;
                d = (d + 1) % 4;
                if (dirCnt % 2 == 0) {
                    maxCnt++;
                }
                cnt = 0;
            }
            i++;
        }
    }

    static void clearBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = 1;
        for (int tc = 1; tc <= testcase; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 4; i++) {
                cnts[i] = 0;
            }

            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st =  new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < M; i++) {
                st =  new StringTokenizer(in.readLine(), " ");
                int d = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());
                solution(d, s);
            }

            System.out.println(cnts[1] + cnts[2] * 2 + cnts[3] * 3);
        }
    }
}
