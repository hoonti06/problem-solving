package boj.p1XXX.p1913;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int curDir; // 현재 방향

    // ^, >, v, <
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};

    static final int MAX_N = 1005;
    static int N, M;
    static int resR, resC;
    static int[][] board = new int[MAX_N][MAX_N];
    static boolean[][] visited = new boolean[MAX_N][MAX_N];

    static void solution() {
        int r = N / 2, c = N / 2; // (N/2, N/2)부터 시작하기 위한 초기화
        board[r][c] = 1;

        int limit = 1;
        int cnt = 0;
        int limitChangedCnt = 0;
        for (int k = 2; k <= N * N; k++) {
            r += dr[curDir];
            c += dc[curDir];
            board[r][c] = k;
            if (k == M) {
                resR = r + 1;
                resC = c + 1;
            }
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++)
//                    System.out.print(board[i][j] + " ");
//                System.out.println();
//            }

            cnt++;

            if (cnt >= limit) {
                curDir = (curDir + 1) % 4;
                cnt = 0;
                limitChangedCnt++;
                if (limitChangedCnt % 2 == 0)
                    limit++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);
        // init
        curDir = 0;
        for (int i = 0; i < MAX_N; i++)
            Arrays.fill(visited[i], false);

        N = sc.nextInt();
        M = sc.nextInt();
        solution();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println(resR + " " + resC);
    }
}

