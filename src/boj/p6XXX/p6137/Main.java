package boj.p6XXX.p6137;

import java.io.*;
import java.util.*;

public class Main {
    static int N;

    static void solution(char[] input) {
        StringBuilder sb = new StringBuilder();

        int left = 0;
        int right = N-1;
        while (left <= right) {
            if (input[left] < input[right]) {
                flush(sb);
                sb.append(input[left++]);
            } else if (input[left] > input[right]) {
                flush(sb);
                sb.append(input[right--]);
            } else {
                int nl = left;
                int nr = right;
                while (input[nl] == input[nr]) {
                    nl++;
                    nr--;
                    if (nl > nr || input[nl] < input[nr]) {
                        flush(sb);
                        sb.append(input[left]);
                        left++;
                        break;
                    } else if (input[nl] > input[nr]) {
                        flush(sb);
                        sb.append(input[right]);
                        right--;
                        break;
                    }
                }
            }
        }
        if (sb.length() != 0) {
            System.out.println(sb);
        }
    }

    static void flush(StringBuilder sb) {
        if (sb.length() == 80) {
            System.out.println(sb);
            sb.setLength(0);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        char[] chars = new char[N];
        for (int i = 0; i < N; i++) {
            chars[i] = in.readLine().charAt(0);
        }
        solution(chars);
    }
}
