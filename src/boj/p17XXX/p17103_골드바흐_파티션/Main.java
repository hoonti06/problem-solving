package boj.p17XXX.p17103_골드바흐_파티션;

import java.io.*;
import java.util.*;

public class Main {
    static int MAX_N = 1_000_001;
    static int N;
    static boolean[] isPrime = new boolean[MAX_N];
    static void getPrimes() {
        Arrays.fill(isPrime, true);

        for (int i = 2; i < MAX_N; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * 2; j < MAX_N; j += i)
                isPrime[j] = false;
        }
    }

    static int solution() {
        int halfN = N/2;
        int cnt = 0;
        for (int i = 2; i <= halfN; i++)
            if (isPrime[i] && isPrime[N-i]) cnt++;

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        getPrimes();
        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            System.out.println(solution());
        }
    }
}
