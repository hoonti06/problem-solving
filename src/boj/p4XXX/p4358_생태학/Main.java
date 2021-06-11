package boj.p4XXX.p4358_생태학;

import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        String str;
        double percent;
        Node(String str, double percent) {
            this.str = str;
            this.percent = percent;
        }

        @Override
        public int compareTo(Node o) {
            return str.compareTo(o.str);
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = 0;
        Map<String, Integer> cnts = new HashMap<>();

        while (true) {
            String str = in.readLine();
            if (str == null || str.isEmpty()) break;
            int cnt = cnts.getOrDefault(str, 0) + 1;
            cnts.put(str, cnt);
            N++;
        }
        Node[] nodes = new Node[cnts.size()];
        int i = 0;
        for (String str : cnts.keySet()) {
            nodes[i++] = new Node(str, (double)(cnts.get(str)) / (double)N * 100.0);
        }
        Arrays.sort(nodes);

        StringBuilder sb = new StringBuilder();
        for (i = 0; i < cnts.size(); i++) {
            sb.append(nodes[i].str).append(" ")
                    .append(String.format("%.4f", nodes[i].percent)).append("\n");
        }
        System.out.print(sb);
    }
}
