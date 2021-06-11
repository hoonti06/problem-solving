package boj.p5XXX.p5430;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            char[] cmds = in.readLine().toCharArray();
            int M = Integer.parseInt(in.readLine());

            String line = in.readLine();
            line = line.replace("[", "").replace("]", "");
            StringTokenizer st = new StringTokenizer(line, ",");
            Deque<Integer> deq = new ArrayDeque<>();
            for (int j = 0; j < M; j++) {
                deq.offer(Integer.parseInt(st.nextToken()));
            }

            int len = cmds.length;
            boolean isFirst = true;
            boolean isError = false;
            for (int j = 0; j < len; j++) {
                char cmd = cmds[j];
                if (cmd == 'R') {
                    isFirst = !isFirst;
                } else {
                    if (deq.isEmpty()) {
                        isError = true;
                        break;
                    }
                    if (isFirst) deq.pollFirst();
                    else deq.pollLast();
                }
            }
            if (isError) {
                sb.append("error\n");
                continue;
            }
            sb.append("[");
            while (!deq.isEmpty()) {
                sb.append(isFirst? deq.pollFirst() : deq.pollLast());
                if (!deq.isEmpty()) sb.append(",");
            }
            sb.append("]\n");
        }
        System.out.println(sb);
    }
}
