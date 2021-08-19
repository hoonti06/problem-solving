package boj.p17XXX.p17829;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;

    static int go(int r, int c, int length) {
        if (length == 1) {
            return board[r][c];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int curLength = length/2;
        int ret1 = go(r, c, curLength);
        int ret2 = go(r + curLength, c, curLength);
        int ret3 = go(r, c + curLength, curLength);
        int ret4 = go(r + curLength, c + curLength, curLength);
        pq.offer(-ret1);
        pq.offer(-ret2);
        pq.offer(-ret3);
        pq.offer(-ret4);

        pq.poll();
        return -pq.poll();
   }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(go(0, 0, N));
    }
}
