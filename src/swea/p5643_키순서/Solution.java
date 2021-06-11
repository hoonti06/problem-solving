package swea.p5643_키순서;

import java.io.*;
import java.util.*;

public class Solution {
    static int N, M;
    static boolean[][] edge1, edge2;
    static boolean[] visited1, visited2;

    static void bfs1(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited1[start] = true;
        while (!q.isEmpty()) {
            int from = q.poll();
            for (int to = 1; to <= N; to++) {
                if (!edge1[from][to] || visited1[to])
                    continue;
                visited1[to] = true;
                q.offer(to);
            }
        }
    }
    static void bfs2(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited2[start] = true;
        while (!q.isEmpty()) {
            int from = q.poll();
            for (int to = 1; to <= N; to++) {
                if (!edge2[from][to] || visited2[to])
                    continue;
                visited2[to] = true;
                q.offer(to);
            }
        }
    }

    static int solution(int start) {
        Arrays.fill(visited1, false);
        Arrays.fill(visited2, false);
        bfs1(start);
        bfs2(start);
        for (int i = 1; i <= N; i++)
            if (!visited1[i] && !visited2[i])
                return 0;
        return 1;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            M = Integer.parseInt(in.readLine());
            edge1 = new boolean[N + 1][N + 1];
            visited1 = new boolean[N+1];
            edge2 = new boolean[N + 1][N + 1];
            visited2 = new boolean[N+1];
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                edge1[a][b] = true;
                edge2[b][a] = true;
            }
            int cnt = 0;
            for (int i = 1; i <= N; i++)
                cnt += solution(i);
            System.out.println("#" + tc + " " + cnt);
        }

    }
}
