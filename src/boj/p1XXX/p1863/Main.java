package boj.p1XXX.p1863;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(in.readLine().split(" ")[1]);
            while (!stack.isEmpty()) {
                int top = stack.peek();
                if (top < cur) {
                    cnt++;
                    stack.push(cur);
                    break;
                } else if (top == cur) {
                    break;
                } else {
                    stack.pop();
                }
            }
        }
        System.out.println(cnt);
    }
}
