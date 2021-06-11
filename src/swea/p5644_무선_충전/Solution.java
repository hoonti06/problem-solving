package swea.p5644_무선_충전;

import java.io.*;
import java.util.*;

public class Solution {
    static final int MAX_N = 11;
    static int M, A;
    static int[][] move;
    // 정지,상,우,하,좌
    static int[] dx = {0, -1, 0, 1,  0};
    static int[] dy = {0,  0, 1, 0, -1};
    static BattteryCharger[] battteryChargers;

    static class BattteryCharger {
        int r, c, dist, point;
        BattteryCharger(int r, int c, int dist, int point) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.point = point;
        }
    }

    static int solution() {
        int sum = 0;
        int ar = 1, ac = 1;
        int br = 10, bc = 10;
        for (int i = 0; i <= M; i++) {
            ar += dx[move[i][0]];
            ac += dy[move[i][0]];
            br += dx[move[i][1]];
            bc += dy[move[i][1]];

            sum += calMax(ar, ac, br, bc);
        }
        return sum;
    }

    static int getPoint(int r, int c, int idx) {
        BattteryCharger bc = battteryChargers[idx];
        return Math.abs(bc.r - r) + Math.abs(bc.c - c) <= bc.dist? bc.point : 0;
    }

    static int calMax(int ar, int ac, int br, int bc) {
        int max = 0;
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < A; j++) {
                int sum = 0;
                int aPoint = getPoint(ar, ac, i);
                int bPoint = getPoint(br, bc, j);

                if (i == j) sum = Math.max(aPoint, bPoint);
                else sum = aPoint + bPoint;

                max = Math.max(max, sum);
            }
        }
        return max;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            move = new int[M+1][2];
            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(in.readLine());
                for (int i = 1; i <= M; i++)
                    move[i][j] = Integer.parseInt(st.nextToken());
            }

            battteryChargers = new BattteryCharger[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(in.readLine());
                int sc = Integer.parseInt(st.nextToken());
                int sr = Integer.parseInt(st.nextToken());
                int depth = Integer.parseInt(st.nextToken());
                int point = Integer.parseInt(st.nextToken());
                battteryChargers[i] = new BattteryCharger(sr, sc, depth, point);
            }

            System.out.println("#" + tc + " " + solution());
        }
    }
}
