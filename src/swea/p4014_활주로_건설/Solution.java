package swea.p4014_활주로_건설;

import java.io.*;
import java.util.*;

public class Solution {
    static int N, X;
    static int[][] board;
    static boolean[][] visited;
    static int solution() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            boolean possible = true;
            for (int j = 0; j < N-1; j++) {
                if (board[i][j] == board[i][j+1]) continue;
                else if (board[i][j] - board[i][j+1] == 1) { // down
                    int k = j+1;
                    int comparison = board[i][j+1];
                    boolean pass = true;
                    for (; k < j+1+X; k++) {
                        if (k >= N || board[i][k] != comparison || visited[i][k]) {
                            pass = false;
                            break;
                        }
                        visited[i][k] = true;
                    }
                    if (!pass) {
                        possible = false;
                        break;
                    }
                    j = k-2;
                }
                else if (board[i][j] - board[i][j+1] == -1) { // up
                    int k = j;
                    int comparison = board[i][j];
                    boolean pass = true;
                    for (; k >= j-X+1; k--) {
                        if (k < 0 || board[i][k] != comparison || visited[i][k]) {
                            pass = false;
                            break;
                        }
                        visited[i][k] = true;
                    }
                    if (!pass) {
                        possible = false;
                        break;
                    }
                }
                else {
                    possible = false;
                    break;
                }
            }
            if (possible) cnt++;
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        for (int j = 0; j < N; j++) {
            boolean possible = true;
            for (int i = 0; i < N-1; i++) {
                if (board[i][j] == board[i+1][j]) continue;
                else if (board[i][j] - board[i+1][j] == 1) { // down
                    int k = i+1;
                    int comparison = board[i+1][j];
                    boolean pass = true;
                    for (; k < i+1+X; k++) {
                        if (k >= N || board[k][j] != comparison || visited[k][j]) {
                            pass = false;
                            break;
                        }
                        visited[k][j] = true;
                    }
                    if (!pass) {
                        possible = false;
                        break;
                    }
                    i = k-2;
                }
                else if (board[i][j] - board[i+1][j] == -1) { // up
                    int k = i;
                    int comparison = board[i][j];
                    boolean pass = true;
                    for (; k >= i-X+1; k--) {
                        if (k < 0 || board[k][j] != comparison || visited[k][j]) {
                            pass = false;
                            break;
                        }
                        visited[k][j] = true;
                    }
                    if (!pass) {
                        possible = false;
                        break;
                    }
                }
                else {
                    possible = false;
                    break;
                }
            }
            if (possible) cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; j++)
                    board[i][j] = Integer.parseInt(st.nextToken());
            }
            System.out.println("#" + tc + " " + solution());
        }

    }
}

