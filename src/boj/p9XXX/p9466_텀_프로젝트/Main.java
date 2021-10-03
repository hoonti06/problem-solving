package boj.p9XXX.p9466_텀_프로젝트;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] input, indegree;
    static int solution() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt++;

            int next = input[cur];
            if (--indegree[next] <= 0) {
                q.offer(next);
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            input = new int[N+1];
            indegree = new int[N+1];
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int i = 1; i <= N; i++) {
                input[i] = Integer.parseInt(st.nextToken());
                indegree[input[i]]++;
            }
            sb.append(solution()).append("\n");
        }
        System.out.print(sb);
    }
}
