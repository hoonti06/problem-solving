package boj.p15XXX.p15650_N과M_2;


import java.util.Scanner;

public class Main {
    static int N, R;
    static int[] res = new int[10];

    static void combi(int cnt, int start) {
        if (cnt >= R) {
            for (int i = 0; i < R; i++)
                System.out.print(res[i] + " ");
            System.out.println();
            return;
        }

        for (int i = start; i <= N; i++) {
            res[cnt] = i;
            combi(cnt + 1, i+1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();

        combi(0, 1);
    }
}

