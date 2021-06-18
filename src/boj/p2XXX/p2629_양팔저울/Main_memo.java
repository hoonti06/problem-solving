package boj.p2XXX.p2629_양팔저울;

import java.io.*;
import java.util.*;

public class Main_memo {
    static final int OFFSET = 15010; // 추 30(개) x 500(g) = 15000
    static int N, M;
    static int[] weight = new int[35];
    static boolean[][] memo = new boolean[35][OFFSET * 2];

    static void go(int idx, int curWeight) {
        if (memo[idx][curWeight]) return;
        memo[idx][curWeight] = true;

        if (idx >= N) return; // idx가 N 이상인지 여기서 체크해야 한다

        go(idx + 1, curWeight + weight[idx+1]); // 왼쪽에 두기
        go(idx + 1, curWeight - weight[idx+1]); // 오른쪽에 두기
        go(idx + 1, curWeight); // 안 두기
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int weightSum = 0;
        for (int i = 1; i <= N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            weightSum += weight[i];
        }

        go(0, OFFSET);

        M = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int input = Integer.parseInt(st.nextToken());
            String res = "N";
            if (input <= weightSum) { // 최대 나올 수 있는 값보다 크면 skip
                for (int j = 1; j <= N; j++) {
                    if (memo[j][OFFSET + input]) {
                        res = "Y";
                        break;
                    }
                }
            }
            sb.append(res).append(" ");
        }
        System.out.println(sb);
    }
}

