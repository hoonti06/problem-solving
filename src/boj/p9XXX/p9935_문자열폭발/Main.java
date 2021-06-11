package boj.p9XXX.p9935_문자열폭발;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[] text, key;
    static boolean[] deleted;

    static Stack<Node> stack;
    static class Node {
        int textIdx, keyIdx;
        Node(int textIdx, int keyIdx) {
            this.textIdx = textIdx;
            this.keyIdx = keyIdx;
        }
    }

    static void solution() {
        int kIdx = 0;
        for (int tIdx = 0; tIdx < N; tIdx++) {
            if (text[tIdx] == key[kIdx]) {
                stack.push(new Node(tIdx, kIdx));
                kIdx++;
                if (kIdx == M) {
                    for (int k = 0; k < M; k++)
                        deleted[stack.pop().textIdx] = true;
                    kIdx = stack.isEmpty()? 0 : stack.peek().keyIdx + 1;
                }
            } else {
                if (kIdx > 0) tIdx--;
                else stack.clear();
                kIdx = 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        text = in.readLine().toCharArray();
        key = in.readLine().toCharArray();
        N = text.length;
        M = key.length;
        deleted = new boolean[N];
        stack = new Stack<>();

        solution();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            if (!deleted[i]) sb.append(text[i]);
        System.out.println(sb.length() == 0? "FRULA" : sb);
    }
}
