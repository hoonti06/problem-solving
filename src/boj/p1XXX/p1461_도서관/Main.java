package boj.p1XXX.p1461_도서관;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> list1, list2;
    static long solution() {
        list1.sort(Comparator.reverseOrder());
        list2.sort(Comparator.reverseOrder());

        int res = 0;
        int cnt = 0;
        int maxDist = 0;
        for (int i = 0; i < list1.size(); i++) {
            if (cnt == 0) {
                int dist = list1.get(i);
                maxDist = Math.max(maxDist, dist);
                res += dist * 2;
            }
            if (++cnt == M) cnt = 0;
        }

        cnt = 0;
        for (int i = 0; i < list2.size(); i++) {
            if (cnt == 0) {
                int dist = list2.get(i);
                maxDist = Math.max(maxDist, dist);
                res += dist * 2;
            }
            if (++cnt == M) cnt = 0;
        }

        return res - maxDist;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            if (input < 0)
                list1.add(-input);
            else
                list2.add(input);
        }
        System.out.println(solution());
    }
}
