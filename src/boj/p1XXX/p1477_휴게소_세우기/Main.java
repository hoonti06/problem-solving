package boj.p1XXX.p1477_휴게소_세우기;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, L;
    static int[] input;

    static boolean possible(int mid) {
        int cnt = M;
        int lastPlace = input[0];
        int i = 1;
        while (i < N) {
            int placeToBuild = lastPlace + mid;
            if (placeToBuild < input[i]) {
                lastPlace = placeToBuild;
                cnt--;
            } else {
                lastPlace = input[i];
                i++;
            }
        }
        return cnt >= 0;
    }

    static int solution() {
        Arrays.sort(input);
        int left = 1;
        int right = 0;
        for (int i = 1; i < N; i++)
            right = Math.max(right, input[i] - input[i-1]);

        int res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (possible(mid)) {
                res = mid;
                right = mid - 1;
            }
            else
                left = mid + 1;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        N += 2;
        input = new int[N];
        input[0] = 0;
        input[1] = L;
        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 2; i < N; i++)
            input[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution());
    }
}