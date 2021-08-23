package boj.p15XXX.p15787;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(0);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int op = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken()) - 1;
            int s = -1;
            if (st.hasMoreTokens()) {
                s = Integer.parseInt(st.nextToken()) - 1;
            }
            int num = list.get(t);
            switch (op) {
                case 1:
                    num |= (1 << s);
                    list.set(t, num);
                    break;
                case 2:
                    num &= ~(1 << s);
                    list.set(t, num);
                    break;
                case 3:
                    num <<= 1;
                    num &= ~(1 << 20);
                    list.set(t, num);
                    break;
                case 4:
                    num >>= 1;
                    list.set(t, num);
                    break;
                default:
                    break;
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(list.get(i));
        }
        System.out.println(set.size());
    }
}
