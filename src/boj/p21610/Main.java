package boj.p21610;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static int[] dx = { 0, -1, -1, -1, 0, 1, 1,  1};
    static int[] dy = {-1, -1,  0,  1, 1, 1, 0, -1};


    static Queue<Node> q;
    static class Node {
        int r, c;
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int hashCode() {
            int hash = 31;
            hash = hash * r;
            hash = hash * c + 7;
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Node) {
                Node other = (Node) o;
                return this.r == other.r && this.c == other.c;
            }
            return false;
        }
    }


    static void solution(int d, int s) {
        Set<Node> set = new HashSet<>();
        while (!q.isEmpty()) {
            for (int qs = 0, qSize = q.size(); qs < qSize; qs++) {
                Node node = q.poll();
                node.r = (node.r + dx[d] * s + N * s) % N;
                node.c = (node.c + dy[d] * s + N * s) % N;

                board[node.r][node.c]++;
                set.add(node);
            }
        }

        for (Node node : set) {
            int cnt = 0;
            for (int dd = 1; dd < 8; dd+=2) {
                int nr = node.r + dx[dd];
                int nc = node.c + dy[dd];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] <= 0) {
                    continue;
                }
                cnt++;
            }
            board[node.r][node.c] += cnt;
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                Node newNode = new Node(r, c);
                if (board[r][c] >= 2 && !set.contains(newNode)) {
                    board[r][c] -= 2;
                    q.offer(newNode);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int testcase = 1;
        for (int tc = 1; tc <= testcase; tc++) {
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            q = new ArrayDeque<>();
            q.offer(new Node(N-2, 0));
            q.offer(new Node(N-2, 1));
            q.offer(new Node(N-1, 0));
            q.offer(new Node(N-1, 1));

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                int d = Integer.parseInt(st.nextToken())-1;
                int s = Integer.parseInt(st.nextToken());
                solution(d, s);
            }

            int sum = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    sum += board[r][c];
                }
            }
            System.out.println(sum);
        }
    }
}
