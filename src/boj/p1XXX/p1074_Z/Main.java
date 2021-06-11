package boj.p1XXX.p1074_Z;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

    static int N;
    static int num;
    static int res = -1;
    static int last;
    //    static int[][] board;
    static int ER, EC;

    static void go(int sr, int sc, int size, int num) {
        if (size <= 1) {
            if (sr == ER && sc == EC)
                res = num;
            return;
        }

        int newSize = size / 2;
        int square = newSize * newSize;
        for (int r = sr, i = 0; i < 2; i++, r += newSize) {
            for (int c = sc, j = 0; j < 2; j++, c += newSize) {
                if (r <= ER && ER < r + newSize && c <= EC && EC < c + newSize) {
                    go(r, c, newSize, num);
                    return;
                }
                num += square;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);
        N = 1 << sc.nextInt();
        ER = sc.nextInt();
        EC = sc.nextInt();
        last = N * N - 1;
//        System.setIn(new FileInputStream("in.txt"));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
//        N = 1 << Integer.parseInt(st.nextToken());
//        ER = Integer.parseInt(st.nextToken());
//        EC = Integer.parseInt(st.nextToken());

//        board = new int[N][N];

        go(0, 0, N, 0);
        System.out.println(res);

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
    }
}

