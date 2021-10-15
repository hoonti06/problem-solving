package boj.p1XXX.p1713;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] input;
    static int[] cnts;
    static int[] saved;
    static int[] order;

    static void solution() {
        for (int i = 0; i < M; i++) {
            int num = input[i];
            int emptyIdx = -1;
            int minCnt = 1234567890;
            int minIdx = -1;
            for (int j = 0; j < N; j++) {
                if (saved[j] == num) {
                    cnts[num]++;
                    break;
                } else if (saved[j] == 0) {
                    emptyIdx = j;
                } else {
                    minCnt = Math.min(minCnt, cnts[num]);
                    minIdx = j;
                }
            }
            if (emptyIdx != -1) {
                saved[emptyIdx] = num;
                cnts[num] = 1;
            } else {
                cnts[saved[minIdx]] = 0;
                saved[minIdx] = num;
                cnts[num] = 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        saved = new int[N];
        cnts = new int[101];
        input = new int[M];
        order = new int[M];

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < M; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        solution();
        Arrays.sort(saved);
        StringBuilder sb = new StringBuilder();
        for (int num : saved) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
