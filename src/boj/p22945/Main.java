package boj.p22945;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] input;

    static int solution() {
        int res = 0;

        int left = 0, right = N-1;
        int len = N-2;
        while (left < right) {
            int min = Math.min(input[left], input[right]);
            res = Math.max(res, len * min);

            if (input[left] < input[right]) {
                left++;
            } else {
                right--;
            }
            len--;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        input = new int[N];

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }
}
