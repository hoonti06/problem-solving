package boj.p15XXX.p15654_N과M_5;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static int N, R;
    static int[] input;
    static int[] res = new int[10];
    static Set<Integer> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    static void combi(int cnt) {
        if (cnt >= R) {
            for (int i = 0; i < R; i++)
                sb.append(res[i]).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (set.contains(input[i])) continue;
            res[cnt] = input[i];
            set.add(input[i]);
            combi(cnt + 1);
            set.remove(input[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        input = new int[N];
        for (int i = 0; i < N; i++)
            input[i] = sc.nextInt();
        Arrays.sort(input);

        combi(0);
        System.out.print(sb);
    }
}

