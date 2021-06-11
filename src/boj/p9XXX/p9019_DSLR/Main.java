package boj.p9XXX.p9019_DSLR;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int start, end;
    static StringBuilder res = new StringBuilder();
    static boolean[] visited = new boolean[10000];

    static class Node {
        int num;
        StringBuilder sb;

        Node(int num, String order) {
            this.num = num;
            sb = new StringBuilder(order);
        }
    }

    static String bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(start, ""));
        visited[start] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                Node cur = q.poll();
                if (cur.num == end) return cur.sb.toString();

                // D
                int nextNum = (cur.num * 2) % 10000;
                if (!visited[nextNum]) {
                    visited[nextNum] = true;
                    String str = cur.sb.toString() + "D";
                    q.offer(new Node(nextNum, str));
                }

                // S
                nextNum = (cur.num == 0) ? 9999 : cur.num - 1;
                if (!visited[nextNum]) {
                    visited[nextNum] = true;
                    String str = cur.sb.toString() + "S";
                    q.offer(new Node(nextNum, str));
                }

                // L
                nextNum = (cur.num % 1000) * 10 + (cur.num / 1000);
                if (!visited[nextNum]) {
                    visited[nextNum] = true;
                    String str = cur.sb.toString() + "L";
                    q.offer(new Node(nextNum, str));
                }

                // R
                nextNum = (cur.num % 10) * 1000 + (cur.num / 10);
                if (!visited[nextNum]) {
                    visited[nextNum] = true;
                    String str = cur.sb.toString() + "R";
                    q.offer(new Node(nextNum, str));
                }
            }

        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            res.setLength(0);
            Arrays.fill(visited, false);

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            System.out.println(bfs());

        }


    }
}
