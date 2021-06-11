package boj.p17XXX.p17471_개리맨더링;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final boolean A = true;
    static final boolean B = false;

    static int N, res;
    static int[] people;
    static boolean[][] edge;
    static boolean[] team, visited;

    static void calSum() {
        Arrays.fill(visited, false);
        int aCnt = 0, bCnt = 0;
        int aSum = 0, bSum = 0;
        for (int i = 1; i <= N; i++) {
            if (team[i] == A) aSum += people[i];
            else bSum += people[i];

            if (visited[i]) continue;
            if (team[i] == A) aCnt++;
            else bCnt++;

            bfs(i);
        }
        if (aCnt == 1 && bCnt == 1)
            res = Math.min(res, Math.abs(aSum - bSum));
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int from = q.poll();
            for (int to = 1; to <= N; to++) {
                if (!edge[from][to] || team[from] != team[to])
                    continue;
                if (visited[to]) continue;

                visited[to] = true;
                q.offer(to);
            }
        }
    }

    static void dfs(int idx) {
        if (idx >= N) {
            calSum();
            return;
        }
        team[idx] = A; dfs(idx+1);
        team[idx] = B; dfs(idx+1);
    }

    static void solution() {
        int NN = (int) Math.pow(2, N);
        Set<Integer> set = new HashSet<>();
        for (int bitmask = 0; bitmask < NN; bitmask++) {
            if (set.contains(bitmask)) continue;
            set.add(bitmask);
            set.add(bitmask^(NN-1));

            for (int digit = 0; digit < N; digit++) {
                if ((bitmask & (1 << digit)) > 0) team[digit+1] = A;
                else team[digit+1] = B;
            }
            calSum();
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        res = Integer.MAX_VALUE;

        N = Integer.parseInt(in.readLine());
        int maxN = N + 1;
        people = new int[maxN];
        team = new boolean[maxN];
        visited = new boolean[maxN];
        edge = new boolean[maxN][maxN];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 1; i <= N; i++)
            people[i] = Integer.parseInt(st.nextToken());

        for (int from = 1; from <= N; from++) {
            st = new StringTokenizer(in.readLine(), " ");
            int M = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M; j++) {
                int to = Integer.parseInt(st.nextToken());
                edge[from][to] = true;
            }
        }

        solution();
        System.out.println(res == Integer.MAX_VALUE? -1 : res);
    }
}