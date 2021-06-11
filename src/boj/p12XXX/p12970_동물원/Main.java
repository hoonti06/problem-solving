package boj.p12XXX.p12970_동물원;

import java.io.*;
import java.util.*;

public class Main {
    static int N, max;
    static int[] input, cnt;
    static int solution() {
        if (cnt[0] > 2 || cnt[0] <= 0) return 0;
        int res = cnt[0];
        for (int i = 1; i <= max; i++) {
            if (cnt[i] > 2 || cnt[i] <= 0 || cnt[i-1] < cnt[i])
                return 0;

            res *= cnt[i];
        }
        if (cnt[max] == 1) res *= 2;
        return res;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        input = new int[N];
        cnt = new int[41];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, input[i]);

            cnt[input[i]]++;
        }
        System.out.println(solution());
    }
}
