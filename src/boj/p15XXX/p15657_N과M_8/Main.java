package boj.p15XXX.p15657_N과M_8;

import java.util.Arrays;
import java.util.Scanner;

import static java.util.Arrays.sort;

public class Main {
    static int N, M;
    //    static int res;
    static int[] input;
    static int[] res = new int[10];

    static void go(int cnt, int startIdx) {
        if (cnt >= M) {
            for (int i = 0; i < M; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = startIdx; i < N; i++) {
            res[cnt] = input[i];
            go(cnt + 1, i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }
        Arrays.sort(input);
//        for (int i = 0; i < N; i++) {
//            System.out.println(input[i]);
//        }

        go(0, 0);
    }
}