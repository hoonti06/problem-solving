package boj.p17XXX.p17073;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] tree;
    static boolean[] visited;

    static class Vertex {
        int num;
        double weight;
        Vertex(int num, double weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    static double solution() {
        tree[1].add(0);
        visited[0] = true;

        Queue<Vertex> q = new ArrayDeque<>();
        q.offer(new Vertex(1, (double) M));

        double sum = 0;
        int leafCnt = 0;
        while (!q.isEmpty()) {
            for (int qs = 0, qSz = q.size(); qs < qSz; qs++) {
                Vertex parent = q.poll();
                visited[parent.num] = true;

                int len = tree[parent.num].size();
                if (len == 1) {
                    leafCnt++;
                    sum += parent.weight;
                    continue;
                }

                double nextWeight = parent.weight / (len-1);
                for (int i = 0; i < len; i++) {
                    int child = tree[parent.num].get(i);
                    if (visited[child]) continue;
                    q.offer(new Vertex(child, nextWeight));
                }
            }
        }
        return sum / (double) leafCnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new List[N+1];
        visited = new boolean[N+1];
        for (int i = 0; i <= N; i++)
            tree[i] = new ArrayList<>();

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        System.out.println(solution());
    }
}
