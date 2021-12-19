package boj.p20366;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] input;
    static int res = Integer.MAX_VALUE;
    static void solution() {
        Arrays.sort(input);
        for (int i = 0; i < N-3; i++) {
            for (int j = i+3; j < N; j++) {
                int first = input[i] + input[j];
                int left = i+1;
                int right = j-1;

                while (left < right) {
                    int second = input[left] + input[right];
                    int diff = first - second;
                    res = Math.min(res, Math.abs(diff));
                    if (diff < 0) {
                        right--;
                    } else if (diff > 0) {
                        left++;
                    } else {
                        return;
                    }
                }
            }
        }
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

        solution();
        System.out.println(res);
    }
}
