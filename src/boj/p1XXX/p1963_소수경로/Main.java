package boj.p1XXX.p1963_소수경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int start, end;
    static boolean[] visited = new boolean[10000];
    static boolean[] isPrime = new boolean[10000];

    static void setPrimes() {
        Arrays.fill(isPrime, true);

        isPrime[1] = false;
        for (int i = 2; i < 10000; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * 2; j < 10000; j += i)
                isPrime[j] = false;
        }
    }

    static String bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                int cur = q.poll();
                if (cur == end)
                    return Integer.toString(cnt);

                for (int digit = 0; digit < 4; digit++) {
                    int mul = (int) Math.pow(10, digit);
                    int prevNum = (cur / mul) % 10;
                    for (int num = 0; num < 10; num++) {
                        int next = cur + (num - prevNum) * mul;

                        if (next < 1000 || !isPrime[next] || visited[next])
                            continue;

                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
            cnt++;
        }
        return "Impossible";
    }


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        setPrimes();

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            Arrays.fill(visited, false);

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            System.out.println(bfs());
        }
    }
}
