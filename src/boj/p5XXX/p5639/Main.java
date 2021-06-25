package boj.p5XXX.p5639;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] input = new int[10010];
    static StringBuilder sb = new StringBuilder();

    static void preorder(int start, int end) {
        if (start >= N) return;
        int num = input[start];

        if (start < end) {
            int i = start + 1;
            for (; i <= end; i++) {
                if (num < input[i])
                    break;
            }

            preorder(start + 1, i - 1);
            if (i <= end) {
                preorder(i, end);
            }
        }
        if (start <= end)
            sb.append(num).append("\n");
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;
        while (true) {
            String line = in.readLine();
            if (line == null || line.length() == 0) break;
            input[i++] = Integer.parseInt(line);
        }
        N = i;
        preorder(0, i-1);
        System.out.println(sb);
    }
}
