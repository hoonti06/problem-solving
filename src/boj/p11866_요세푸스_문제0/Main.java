package boj.p11866_요세푸스_문제0;

import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static List<Integer> list;

    static void solution() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int cur = 0;
        int cnt = 1;
        while (list.size() > 0) {
            cur = (cur + K-1) % list.size();
            sb.append(list.get(cur)).append(", ");
            list.remove(cur);
            cnt++;
        }
        sb.setLength(sb.length()-2);
        sb.append(">");
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for (int i = 1; i <= N; i++) list.add(i);

        solution();
    }
}

