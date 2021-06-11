package boj.p15XXX.p15961_회전_초밥;

import java.io.*;
import java.util.*;

public class Main {
    static int N, D, K, C;
    static int[] input, cnt;

    static int solution() {
        int res = 0, sum = 0;
        for (int i = 0; i < K; i++)
            if (++cnt[input[i]] == 1) sum++;
        res = sum + (cnt[C] == 0? 1 : 0);

        for (int cur = K, prev = 0; cur < N+K; cur++, prev++) {
            if(--cnt[input[prev]] == 0) sum--;
            if(++cnt[input[cur]] == 1) sum++;
            res = Math.max(res, sum + (cnt[C] == 0? 1 : 0));
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

        input = new int[N + K+1];
        cnt = new int[D+1];
        for (int i = 0; i < N; i++)
            input[i] = Integer.parseInt(in.readLine());
        for (int i = 0; i < K; i++)
            input[N+i] = input[i];

        System.out.println(solution());
    }
}
