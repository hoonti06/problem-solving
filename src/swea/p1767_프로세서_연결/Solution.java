package swea.p1767_프로세서_연결;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static boolean[][] visited;
    static List<Node> processors;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int resCnt;
    static int resSum;

    static List<Node> visit(int r, int c, int dir) {
        List<Node> res = new LinkedList<>();
        while (true) {
            r += dx[dir];
            c += dy[dir];
            if (r < 0 || r >= N || c < 0 || c >= N) break;

            if (visited[r][c]) {
                for (int i = 0; i < res.size(); i++) {
                    Node cur = res.get(i);
                    visited[cur.r][cur.c] = false;
                }
                return null;
            }
            visited[r][c] = true;
            res.add(new Node(r, c));
        }
        return res;
    }

    static void undo(int r, int c, List<Node> pos) {
        for (int i = 0; i < pos.size(); i++) {
            Node node = pos.get(i);
            visited[node.r][node.c] = false;
        }
    }

    static void backtracking(int idx, int cnt, int sum) {
        if (idx >= processors.size()) {
            if (resCnt < cnt) {
                resCnt = cnt;
                resSum = sum;
            } else if (resCnt == cnt)
                resSum = Math.min(resSum, sum);
            return;
        }

        Node cur = processors.get(idx);
        if (cur.r == 0 || cur.r == N - 1 || cur.c == 0 || cur.c == N - 1)
            backtracking(idx + 1, cnt + 1, sum);
        else {
            for (int i = 0; i < 4; i++) {
                List<Node> visitedPos = visit(cur.r, cur.c, i);
                if (visitedPos == null) continue;

                backtracking(idx + 1, cnt + 1, sum + visitedPos.size());
                undo(cur.r, cur.c, visitedPos);
            }
            backtracking(idx + 1, cnt, sum);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            resCnt = 0;
            resSum = Integer.MAX_VALUE;

            N = Integer.parseInt(in.readLine());
            visited = new boolean[N][N];
            processors = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    if (Integer.parseInt(st.nextToken()) == 1) {
                        visited[i][j] = true;
                        processors.add(new Node(i, j));
                    }
                }
            }
            backtracking(0, 0, 0);
            System.out.println("#" + tc + " " + resSum);
        }
    }
}


