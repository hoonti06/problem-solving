package boj.p20304_비밀번호_찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] input, dists;

    static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            q.offer(input[i]);
            dists[input[i]] = 0;
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int digit = 0; digit <= 20; digit++) {
                int next = cur ^ (1 << digit);
                if (next <= N && dists[next] == -1) {
                    dists[next] = dists[cur] + 1;
                    q.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());

        input = new int[M];
        dists = new int[N+1];
        Arrays.fill(dists, -1);
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < M; i++)
            input[i] = Integer.parseInt(st.nextToken());

        bfs();

        int res = 0;
        for (int i = 0; i <= N; i++)
            res = Math.max(res, dists[i]);
        System.out.println(res);
    }
}
