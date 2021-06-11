package boj.p16XXX.p16926_배열돌리기1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_N = 305;
    static int N, M, R;
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[][] board = new int[MAX_N][MAX_N];
    static boolean[][] visited = new boolean[MAX_N][MAX_N];


    static void solution() {
        int maxDepth = Math.min(N, M) / 2;
        for (int start = 0; start < maxDepth; start++) {
            int r = start;
            int c = start;
            int saved = board[start][start];
            int curDir = 0;
            while (true) {
                int nr = r + dx[curDir];
                int nc = c + dy[curDir];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
                    curDir = (curDir + 1) % 4;
                    continue;
                }
                visited[nr][nc] = true;

                int tmp = board[nr][nc];
                board[nr][nc] = saved;
                saved = tmp;

                if (nr == start && nc == start)
                    break;

                r = nr;
                c = nc;
            }
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < M; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < N; j++)
                Arrays.fill(visited[j], false);

            solution();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }
}
