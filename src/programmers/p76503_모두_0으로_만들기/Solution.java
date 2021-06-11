package programmers.p76503_모두_0으로_만들기;

import java.util.*;

public class Solution {
    public long solution(int[] a, int[][] edges) {
        long sum = 0;
        for (int e : a) sum += e;
        if (sum != 0) return -1;

        int N = a.length;
        int M = edges.length;
        int[] indegree = new int[N];
        List<Integer>[] nodes = new ArrayList[N];
        for (int i = 0; i < N; i++)
            nodes[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            nodes[from].add(to);
            nodes[to].add(from);
            indegree[from]++;
            indegree[to]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++){
            if (indegree[i] == 1) {
                q.offer(i);
                indegree[i] = 0;
            }
        }

        long[] b = new long[N];
        for (int i = 0; i < N; i++) b[i] = a[i];

        long result = 0;
        while (!q.isEmpty()) {
            int from = q.poll();
            visited[from] = true;
            result += Math.abs(b[from]);
            int size = nodes[from].size();
            for (int i = 0; i < size; i++) {
                int to = nodes[from].get(i);
                if (visited[to]) continue;
                indegree[to]--;

                b[to] += b[from]; // int[] a로 연산할 경우 overflow 발생하여 틀린 답 리턴
                b[from] = 0;
                if (indegree[to] == 1) {
                    indegree[to] = 0;
                    q.offer(to);
                }
            }
        }

        for (int i = 0; i < N; i++)
            if (b[i] != 0) return -1;
        return result;
    }

}