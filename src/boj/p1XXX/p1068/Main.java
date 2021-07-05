package boj.p1XXX.p1068;

import java.io.*;
import java.util.*;

public class Main {
    static int N, del;
    static int[] input;
    static List<Integer>[] tree;

    static int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        int cnt = 0;
        while (!q.isEmpty()) {
            int parent = q.poll();
            if (parent == del) {
                continue;
            }

            int size = tree[parent].size();
            if (size == 0) {
                cnt++;
                continue;
            }
            else if (size == 1) {
                if (tree[parent].get(0) == del) {
                    cnt++;
                    continue;
                }
            }
            for (int i = 0; i < size; i++) {
                int child = tree[parent].get(i);
                q.offer(child);
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        input = new int[N];
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++)
            tree[i] = new ArrayList<>();

        int start = 0;
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                start = i;
            }
            else {
                tree[parent].add(i);
            }
        }
        del = Integer.parseInt(in.readLine());

        System.out.println(bfs(start));
    }
}
