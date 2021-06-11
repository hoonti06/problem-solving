package boj.p5XXX.p5052;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String[] input;

    static class Trie {
        private TrieNode root;
        Trie() {
            root = new TrieNode();
        }

        boolean insert(String key) {
            TrieNode curNode = root;
            int length = key.length();

            for (int i = 0; i < length; i++) {
                int next = key.charAt(i) - '0';

                if (curNode.next[next] == null) {
                    curNode.next[next] = new TrieNode();
                }
                curNode = curNode.next[next];

                if (curNode.isTerminal && i + 1 < length)
                    return false;
            }
            curNode.isTerminal = true;
            return true;
        }
    }

    static class TrieNode {
        TrieNode[] next;
        boolean isTerminal;
        TrieNode() {
            next = new TrieNode[10];
            isTerminal = false;
        }
    }

    static boolean solution() {
        Arrays.sort(input);

        Trie root = new Trie();
        for (int i = 0; i < N; i++) {
            if (!root.insert(input[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            input = new String[N];
            for (int i = 0; i < N; i++) {
                input[i] = in.readLine();
            }
            sb.append(solution() ? "YES" : "NO").append("\n");
        }
        System.out.print(sb);
    }
}

