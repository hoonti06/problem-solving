package boj.p2XXX.p2470;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] input;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        input = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        int left = 0;
        int right = N-1;
        int min = Integer.MAX_VALUE;
        int res1 = 0;
        int res2 = 0;
        while (left < right) {
            int sum = input[left] + input[right];
            int absSum = Math.abs(sum);
            if (min > absSum) {
                min = absSum;
                res1 = input[left];
                res2 = input[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(res1 + " " + res2);
    }
}
