package boj.p20056;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] board;
    static int[] dx = {-1, -1, 0, 1, 1,  1,  0, -1};
    static int[] dy = { 0,  1, 1, 1, 0, -1, -1, -1};
    static Queue<Node> q;

    static class Node {
        int r, c, m, s, d;
        Node(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int solution() {
        List<Node>[][] nodeList = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nodeList[i][j] = new ArrayList<>();
            }
        }

        while (K-- > 0 && !q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                Node cur = q.poll();
                cur.r = (cur.r + dx[cur.d] * cur.s + N * cur.s) % N;
                cur.c = (cur.c + dy[cur.d] * cur.s + N * cur.s) % N;

                nodeList[cur.r][cur.c].add(cur);
            }
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int cnt = nodeList[r][c].size();
                    if (cnt >= 2) {
                        int mSum = 0;
                        int sSum = 0;
                        int dSum = 0;
                        for (Node node : nodeList[r][c]) {
                            mSum += node.m;
                            sSum += node.s;
                            dSum += node.d % 2;
                        }
                        int m = (int) Math.floor((double) mSum / 5);
                        int s = (int) Math.floor((double) sSum / cnt);
                        int d = (dSum == 0 || dSum == cnt)? 0 : 1;
                        if (m > 0) {
                            for (int nd = d; nd < 8; nd += 2) {
                                q.offer(new Node(r, c, m, s, nd));
                            }
                        }
                    } else if (cnt == 1) {
                        q.offer(nodeList[r][c].get(0));
                    }
                    nodeList[r][c].clear();
                }
            }
        }

        int res = 0;
        while (!q.isEmpty()) {
            res += q.poll().m;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        q = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            q.offer(new Node(r, c, m, s, d));
        }

        int ret = solution();
        System.out.println(ret);
    }
}
