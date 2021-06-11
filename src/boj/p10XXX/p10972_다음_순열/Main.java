package boj.p10XXX.p10972_다음_순열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] input;
    static void swap(int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    static boolean nextPermutation() {
        int i = N-1;
        while (i-1 >= 0 && input[i-1] >= input[i]) i--;
        if (i <= 0) return false;

        int j = N-1;
        while (input[i-1] >= input[j]) j--;
        swap(i-1, j);

        int k = N-1;
        while (i < k) swap(i++, k--);

        return true;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        input = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++)
            input[i] = Integer.parseInt(st.nextToken());

        if (!nextPermutation())
            System.out.println(-1);
        else
            for (int i = 0; i < N; i++)
                System.out.print(input[i] + " ");
    }
}
