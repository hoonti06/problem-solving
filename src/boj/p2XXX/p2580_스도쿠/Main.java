package boj.p2XXX.p2580_스도쿠;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    static int N = 9;
    static int size;
    static int[][] board = new int[N][N];
    static List<Point> emptyList = new ArrayList<>();
//    static List<Integer> emptyList = new ArrayList<Integer>();

    static List<Integer> getPossibleNums(int r, int c) {
        boolean[] visited = new boolean[10];
        Arrays.fill(visited, false);
        for (int i = 0; i < 9; i++) {
            visited[board[i][c]] = true;
            visited[board[r][i]] = true;
        }

        int sr = r / 3 * 3, sc = c / 3 * 3;
        for (int i = sr; i < sr + 3; i++)
            for (int j = sc; j < sc + 3; j++)
                visited[board[i][j]] = true;

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++)
            if (!visited[i])
                res.add(i);

        return res;
    }

    static boolean dfs(int idx) {
        if (idx >= size) {
            return true;
        }
        Point cur = emptyList.get(idx);
        int r = cur.r;
        int c = cur.c;
        List<Integer> possibleNums = getPossibleNums(r, c);
//        for (int i = 0; i < possibleNums.size(); i++) {
//            System.out.print(possibleNums.get(i) + " ");
//        }
//        System.out.println();


        for (int i = 0; i < possibleNums.size(); i++) {
            board[r][c] = possibleNums.get(i);
            if (dfs(idx + 1))
                return true;
            board[r][c] = 0;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0)
                    emptyList.add(new Point(i, j));
            }
        }
        size = emptyList.size();

        dfs(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                sb.append(board[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
