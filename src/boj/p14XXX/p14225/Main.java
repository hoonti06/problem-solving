package boj.p14XXX.p14225;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] input;
    static boolean[] visited, added;

    static void go(int idx) {
        if (idx >= N) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (added[i]) {
                    sum += input[i];
                }
            }
            visited[sum] = true;
            return;
        }
        added[idx] = true;
        go(idx + 1);
        added[idx] = false;
        go(idx + 1);
    }

    static int solution() {
        go(0);

        int len = visited.length;
        for (int i = 1; i < len; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        visited = new boolean[100_001 * 20];
        N = Integer.parseInt(in.readLine());
        input = new int[N];
        added = new boolean[N];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }
}
