package boj.p1XXX.p1711;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] x, y;
    static long[][] distSquare;

    static long calDistSquare(long x1, long y1, long x2, long y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    // 피타고라스 정리
    static boolean isAngle90(long distSq1, long distSq2, long distSq3) {
        return distSq1 == distSq3 + distSq2
                || distSq2 == distSq1 + distSq3
                || distSq3 == distSq1 + distSq2;
    }

    static int solution() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            long x1 = x[i];
            long y1 = y[i];
            for (int j = i + 1; j < N; j++) {
                long x2 = x[j];
                long y2 = y[j];
                for (int k = j + 1; k < N; k++) {
                    long x3 = x[k];
                    long y3 = y[k];
                    long distSq1 = distSquare[i][j];
                    if (distSquare[i][j] == Long.MIN_VALUE) {
                        distSq1 = distSquare[i][j] = calDistSquare(x1, y1, x2, y2);
                    }

                    long distSq2 = distSquare[j][k];
                    if (distSquare[j][k] == Long.MIN_VALUE) {
                        distSq2 = distSquare[j][k] = calDistSquare(x2, y2, x3, y3);
                    }

                    long distSq3 = distSquare[i][k];
                    if (distSquare[i][k] == Long.MIN_VALUE) {
                        distSq3 = distSquare[i][k] = calDistSquare(x1, y1, x3, y3);
                    }

                    if (isAngle90(distSq1, distSq2, distSq3)) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        x = new long[N];
        y = new long[N];
        distSquare = new long[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distSquare[i], Long.MIN_VALUE);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(solution());
    }
}
