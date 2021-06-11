package boj.p10XXX.p10157_자리배정;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

    // > v < ^
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int num = sc.nextInt();
        visited = new boolean[N][M];
        board = new int[N][M];

        int r = 0, c = -1;
        int dir = 0;
        int len = N * M;
        if (len < num) {
            System.out.println("0");
            return;
        }

        for (int i = 1; i <= len; i++) {
            int nr = r + dx[dir];
            int nc = c + dy[dir];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
                i--;
                dir = (dir + 1) % 4;
                continue;
            }

            if (num == i) {
                System.out.println((nr + 1) + " " + (nc + 1));
                break;
            }

            visited[nr][nc] = true;
            board[nr][nc] = i;
            r = nr;
            c = nc;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%02d ", board[i][j]);
            }
            System.out.println();
        }
    }
}
