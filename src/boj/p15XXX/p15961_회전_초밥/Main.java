package boj.p15XXX.p15961_회전_초밥;

import java.io.*;
import java.util.*;

public class Main {
    static int N, D, K, C;
    static int[] input, cnts;

    static int solution() {
        cnts[C]++;
        int typeCnt = 1;
        for (int i = 0; i < K; i++)
            if (++cnts[input[i]] == 1) typeCnt++;
        int res = typeCnt;

        for (int i = K, prev = 0; i < N+K; i++, prev++) {
            int cur = i % N;
            if(--cnts[input[prev]] == 0) typeCnt--;
            if(++cnts[input[cur]] == 1) typeCnt++;
            res = Math.max(res, typeCnt);
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        input = new int[N];
        cnts = new int[D+1];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(in.readLine());
        }

        System.out.println(solution());
    }
}
