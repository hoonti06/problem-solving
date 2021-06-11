package boj.p1XXX.p1946_신입사원;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<int[]> pair;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            pair = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                pair.add(new int[]{first, second});
            }
            Collections.sort(pair, (a, b) -> Integer.compare(a[0], b[0]));

            int min = pair.get(0)[1];
            int cnt = 0;
            for (int i = 1; i < N; i++) {
                int cur = pair.get(i)[1];
                if (cur > min) cnt++;
                else min = cur;
            }
            System.out.println(N - cnt);
        }
    }
}

