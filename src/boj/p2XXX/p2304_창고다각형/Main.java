package boj.p2XXX.p2304_창고다각형;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] height = new int[1001];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int maxLength = 0;
        N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            String[] str = in.readLine().split(" ");
            int idx = Integer.parseInt(str[0]);
            int h = Integer.parseInt(str[1]);
            maxLength = Math.max(idx + 1, maxLength);
            height[idx] = h;
        }

        int idx = -1;
        int max = 0;
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < maxLength; i++) {
            if (max <= height[i]) {
                sum += cnt * max;
                max = height[i];
                idx = i;
                cnt = 1;
            } else
                cnt++;
        }
        sum += max;

        max = 0;
        cnt = 0;
        int fin = idx;
        idx = maxLength;
        for (int i = maxLength - 1; i > fin; i--) {
            if (max <= height[i]) {
                sum += cnt * max;
                max = height[i];
                idx = i;
                cnt = 1;
            } else
                cnt++;
        }
        sum += cnt * max;
        System.out.println(sum);
    }
}
