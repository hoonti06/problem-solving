package boj.p16XXX.p16678_모독;

import java.io.*;
import java.util.*;

public class Main  {
    static int N;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        int[] cnt = new int[100_001];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(in.readLine());
            min = Math.min(min, num);
            max = Math.max(max, num);
            cnt[num]++;
        }

        int left = 1, right = min;
        long res = 0;
        while (right <= max) {
            if (cnt[left] > 0) {
                left++;
                if (left > right) right++;
            }
            else if (cnt[right] == 0) {
                right++;
            }
            else {
                cnt[right]--;
                cnt[left]++;
                res += right - left;
            }
        }
        System.out.println(res);
    }
}
