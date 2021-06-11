package boj.p17XXX.p17071_숨바꼭질5;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_N = 500_001;
    static int N, K;
    static int[] board = new int[MAX_N];
    static int[][] visited = new int[MAX_N][2];

    static int move() {
        int cur = K, cnt = 0;
        do {
            board[cur] = cnt;
            if (board[cur] >= visited[cur][cnt%2])
                return cnt;

            cnt++;
            cur += cnt;
        } while (cur < MAX_N);

        return -1;
    }

    static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);

        int cnt = 0;
        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                int cur = q.poll();
                if (visited[cur][cnt%2] >= 0) continue;
                visited[cur][cnt%2] = cnt;

                int nCnt = cnt + 1;
                for (int i = 0; i < 3; i++) {
                    int next = cur;
                    if (i == 0) next--;
                    else if (i == 1) next++;
                    else next *= 2;

                    if (next < 0 || next >= MAX_N || visited[next][nCnt%2] > 0)
                        continue;

                    q.offer(next);
                }
            }
            cnt++;
        }
    }

    static int solution() {
        bfs();
        return move();
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Arrays.fill(board, -1);
        for (int i = 0; i < MAX_N; i++)
            Arrays.fill(visited[i], -1);

//        solution();
        System.out.println(solution());
    }
}
