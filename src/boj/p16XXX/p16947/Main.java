package boj.p16XXX.p16947;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<Integer>[] edges;
    static boolean[] isCycle;
    static boolean[] visited;

    static String solution() {
        isCycle = new boolean[N+1];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false);
            dfs(i, -1, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dfs2(i, -1, i)).append(" ");
        }
        return sb.toString();
    }

    static boolean dfs(int start, int prev, int cur) {
        visited[cur] = true;

        for (int next : edges[cur]) {
            if (next == prev) continue;

            if (next == start) {
                isCycle[start] = true;
                return true;
            }

            if (visited[next]) continue;

            if (dfs(start, cur, next)) {
                return true;
            }
        }
        return false;
    }

    static int dfs2(int cnt, int prev, int cur) {
        if (isCycle[cur]) {
            return 0;
        }

        int ret = 1000000;
        for (int next : edges[cur]) {
            if (prev == next) continue;

            int tmp = dfs2(cnt+1, cur, next);
            ret = Math.min(ret, tmp);
        }
        return ret + 1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        edges = new List[3001];
        for (int i = 0; i < 3001; i++) {
            edges[i] = new ArrayList<>();
        }

        N = 0;
        int M = Integer.parseInt(in.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);

            N = Math.max(N, a);
            N = Math.max(N, b);
        }
        System.out.println(solution());
    }
}
