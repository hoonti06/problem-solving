package boj.p2XXX.p2559_수열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] sum = new int[100010];

    static int solution() {
        int res = Integer.MIN_VALUE;
        for (int i = K; i <= N; i++)
            res = Math.max(res, sum[i] - sum[i - K]);

        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            int input = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + input;
        }
        System.out.println(solution());
    }
}
