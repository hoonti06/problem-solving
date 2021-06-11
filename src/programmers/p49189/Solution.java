package programmers.p49189;

import java.util.*;

public class Solution {
    static List<Integer>[] edge;

    public int solution(int n, int[][] input) {
        edge = new ArrayList[n+1];
        for (int i = 0; i <= n; i++)
            edge[i] = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            int from = input[i][0];
            int to = input[i][1];
            edge[from].add(to);
            edge[to].add(from);
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        int[] visited = new int[n+1];
        Arrays.fill(visited, -1);
        visited[1] = 0;

        int cnt = 1;
        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                int from = q.poll();

                for (int i = 0; i < edge[from].size(); i++) {
                    int to = edge[from].get(i);
                    if (visited[to] != -1) continue;
                    visited[to] = cnt;
                    q.offer(to);
                }
            }
            cnt++;
        }

        int answer = 0;
        Arrays.sort(visited);
        int max = visited[n];
        for (int i = n; i >= 1; i--)
            if (visited[i] == max) answer++;

        // max값이 0이면 1과 연결된 정점이 하나도 없다는 의미
        // max가 0이면 answer가 1인 상태이기 때문에 answer를 그대로 리턴하지 않고 0을 리턴해야 한다
        return max <= 0? 0 : answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(6,
                new int[][]{{3, 6}, {4, 3}, {3, 2},
                        {1, 3}, {1, 2}, {2, 4}, {5, 2}});
    }
}
