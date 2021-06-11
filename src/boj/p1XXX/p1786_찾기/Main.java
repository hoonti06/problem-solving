package boj.p1XXX.p1786_찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static char[] text, pattern;
    static int N, M;
    static int[] fail;
    static List<Integer> res = new ArrayList<>();

    static void calFail() {
        for (int i = 1, j = 0; i < M; i++) {
            while (j > 0 && pattern[i] != pattern[j])
                j = fail[j-1];

            if (pattern[i] == pattern[j])
                fail[i] = ++j;
        }
    }

    static void solution() {
        calFail();

        for (int i = 0, j = 0; i < N; i++) {
            while (j > 0 && text[i] != pattern[j])
                j = fail[j-1];

            if (text[i] == pattern[j]) {
                if (j == M-1) {
                    res.add(i - M + 2);
                    j = fail[j];
                } else j++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        text = in.readLine().toCharArray();
        pattern = in.readLine().toCharArray();
        N = text.length;
        M = pattern.length;
        fail = new int[N];

        solution();

        System.out.println(res.size());
        for (int i = 0; i < res.size(); i++)
            System.out.print(res.get(i) + " ");
    }
}
