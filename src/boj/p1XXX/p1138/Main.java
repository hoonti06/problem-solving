package boj.p1XXX.p1138;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] input;
    static int[] res;

    static void solution() {
        for (int i = 1; i <= N; i++) {
            int j = 0;
            int cnt = 0;
            while (cnt < input[i]) {
                if (res[j++] >= i) {
                    cnt++;
                }
            }
            while (res[j] < i) {
                j++;
            }

            res[j] = i;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        input = new int[N+1];
        res = new int[N+1];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(res, Integer.MAX_VALUE);

        solution();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);
    }

}
