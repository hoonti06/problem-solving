package boj.p2XXX.p2473_세_용액;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] input;

    static long[] solution() {
        long minSum = Long.MAX_VALUE;
        long[] res = new long[3];

        Arrays.sort(input);
        for (int i = 0; i < N-2; i++) {
            long first = input[i];
            int left = i+1;
            int right = N-1;

            while (left < right) {
                long sum = first + input[left] + input[right];
                long absSum = Math.abs(sum);
                if (minSum > absSum) {
                    minSum = absSum;
                    res[0] = first;
                    res[1] = input[left];
                    res[2] = input[right];
                }
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    return res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        input = new long[N];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        long[] res = solution();
        System.out.println(res[0] + " " + res[1] + " " + res[2]);
    }
}
