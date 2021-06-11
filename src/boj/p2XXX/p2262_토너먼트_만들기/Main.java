package boj.p2XXX.p2262_토너먼트_만들기;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer> list;

    static int solution() {
        int res = 0;
        for (int num = N; num > 1; num--) {
            int len = list.size();
            int j = 0;
            for (; j < len; j++) {
                if (list.get(j) == num) break;
            }
            int minDiff = Integer.MAX_VALUE;
            int left = j-1;
            if (left >= 0) {
                minDiff = Math.abs(list.get(left) - num);
            }
            int right = j+1;
            if (right < len) {
                minDiff = Math.min(minDiff, Math.abs(list.get(right) - num));
            }
            if (minDiff != Integer.MAX_VALUE) {
                res += minDiff;
            }
            list.remove(j);
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        System.out.println(solution());
    }
}
