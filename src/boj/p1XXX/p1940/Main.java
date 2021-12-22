package boj.p1XXX.p1940;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] input;

    static int solution() {
        int cnt = 0;
        Arrays.sort(input);

        int left = 0, right = N-1;
        while (left < right) {
            int sum = input[left] + input[right];
            int diff = sum - M;
            if (diff == 0) {
                cnt++;
                left++;
                right--;
            } else if (diff > 0) {
                right--;
            } else {
                left++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        input = new int[N];

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}
