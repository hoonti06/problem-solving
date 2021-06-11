package boj.p16XXX.p16935_배열돌리기3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_N = 105;
    static int N, M, R;
    //    static int res;
    static int[][] board;

    static void reverseUpDown() {
        int H = board.length;
        int W = board[0].length;

        for (int j = 0; j < W; j++) {
            for (int i = 0; i < H / 2; i++) {
                int tmp = board[i][j];
                board[i][j] = board[H - 1 - i][j];
                board[H - 1 - i][j] = tmp;
            }
        }
    }

    static void reverseLeftRight() {
        int H = board.length;
        int W = board[0].length;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W / 2; j++) {
                int tmp = board[i][j];
                board[i][j] = board[i][W - 1 - j];
                board[i][W - 1 - j] = tmp;
            }
        }
    }


    static void rotate90() {
        int H = board.length;
        int W = board[0].length;
        int[][] tmpBoard = new int[W][H];

        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                tmpBoard[j][H - 1 - i] = board[i][j];

        board = tmpBoard;
    }

    static void rotateSub() {
        int H = board.length;
        int W = board[0].length;
        int[][] tmpBoard = new int[H][W];

        int halfH = H / 2;
        int halfW = W / 2;

        // >
        for (int i = 0; i < halfH; i++)
            for (int j = 0; j < halfW; j++)
                tmpBoard[i][halfW + j] = board[i][j];
        // v
        for (int i = 0; i < halfH; i++)
            for (int j = halfW; j < W; j++)
                tmpBoard[halfH + i][j] = board[i][j];

        // <
        for (int i = halfH; i < H; i++)
            for (int j = halfW; j < W; j++)
                tmpBoard[i][j - halfW] = board[i][j];

        // ^
        for (int i = halfH; i < H; i++)
            for (int j = 0; j < halfW; j++)
                tmpBoard[i - halfH][j] = board[i][j];

        board = tmpBoard;
    }

    static void solution(int num) {
        switch (num) {
            case 1:
                reverseUpDown();
                break;
            case 2:
                reverseLeftRight();
                break;
            case 3:
                rotate90();
                break;
            case 4:
                for (int i = 0; i < 3; i++)
                    rotate90();
                break;
            case 5:
                rotateSub();
                break;
            case 6:
                for (int i = 0; i < 3; i++)
                    rotateSub();
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < M; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < R; i++) {
            int num = Integer.parseInt(st.nextToken());
            solution(num);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
