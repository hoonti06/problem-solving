package swea.p4335_무인도탈출;

import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int res, size;
    static Node[] nodes;
    static boolean[] visited;

    static class Node implements Comparable<Node> {
        int a, b, c, idx;
        Node(int a, int b, int c, int idx) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            if (this.a != o.a)
                return o.a - this.a;
            if (this.b != o.b)
                return o.b - this.b;
            if (this.c != o.c)
                return o.c - this.c;
            return this.idx - o.idx;
        }
    }


    static void go(int cnt, int start, int prevB, int sum) {
        if (cnt >= N || start >= size) {
            res = Math.max(res, sum);
            return;
        }

        for (int i = start; i < size; i++) {
            Node curNode = nodes[i];
            if (visited[curNode.idx]) continue;

            if (prevB < curNode.b) continue;

            visited[curNode.idx] = true;
            go(cnt+1, i+1, curNode.b, sum + curNode.c);
            visited[curNode.idx] = false;
        }
        res = Math.max(res, sum);
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            res = 0;

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            size = N * 3;
            visited = new boolean[N];
            nodes = new Node[size];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int offset = i * 3;
                nodes[offset+0] = new Node(Math.max(a, b), Math.min(a, b), c, i);
                nodes[offset+1] = new Node(Math.max(a, c), Math.min(a, c), b, i);
                nodes[offset+2] = new Node(Math.max(b, c), Math.min(b, c), a, i);
            }
            Arrays.sort(nodes);

            go(0, 0, Integer.MAX_VALUE, 0);
            System.out.println("#" + tc + " " + res);
//            break;
        }
    }
}
