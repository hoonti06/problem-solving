package boj.p2XXX.p2224;

import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] possible; // possible[i][j] : i에서 j로 갈 수 있으면 true

    static void solution() {
        for (int k = 0; k < 100; k++) {
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (possible[i][k] && possible[k][j]) {
                        possible[i][j] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        possible = new boolean[100][100];

        for (int i = 0; i < N; i++) {
            String[] split = in.readLine().split(" ");
            int from = split[0].charAt(0) - 'A';
            int to = split[2].charAt(0) - 'A';
            possible[from][to] = true;
        }

        solution();

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i == j || !possible[i][j]) continue;
                sb.append((char) (i + 'A')).append(" => ").append((char) (j + 'A')).append("\n");
                cnt++;
            }
        }

        System.out.println(cnt);
        System.out.print(sb);
    }
}
