package swea.p1486_장훈이의_높은_선반;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, B;
    static int[] input;
    static int res;

    static void bactracking(int idx, int sum) {
        if (idx >= N) {
            if (sum >= B)
                res = Math.min(res, sum);
            return;
        }

        bactracking(idx+1, sum + input[idx]);
        bactracking(idx+1, sum);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            res = Integer.MAX_VALUE;

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            input = new int[N];
            st = new StringTokenizer(in.readLine(), " ");
            for (int i = 0; i < N; i++)
                input[i] = Integer.parseInt(st.nextToken());

            bactracking(0, 0);
            System.out.println("#" + tc + " " + (res - B));
        }
    }
}
