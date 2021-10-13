package boj.p16XXX.p16943;

import java.io.*;
import java.util.*;

public class Main {

    public static boolean nextPermutation(int[] perm) {
        int N = perm.length;
        int i = N-1;
        while (i > 0 && perm[i-1] >= perm[i]) {
            i--;
        }
        if (i <= 0) {
            return false;
        }

        int j = N-1;
        while (perm[i-1] >= perm[j]) {
            j--;
        }

        int tmp = perm[i-1];
        perm[i-1] = perm[j];
        perm[j] = tmp;

        int k = N-1;
        while (i < k) {
            tmp = perm[i];
            perm[i++] = perm[k];
            perm[k--] = tmp;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] split = in.readLine().split(" ");
        char[] As = split[0].toCharArray();
        int bNum = Integer.parseInt(split[1]);
        int N = As.length;
        int[] perm = new int[N];
        for (int i = 0; i < N; i++) {
            perm[i] = As[i] - '0';
        }
        Arrays.sort(perm);

        int res = -1;
        do {
            if (perm[0] == 0) {
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for (int each : perm) {
                sb.append(each);
            }
            int aNum = Integer.parseInt(sb.toString());
            if (aNum < bNum) {
                res = Math.max(res, aNum);
            } else {
                break;
            }
        } while(nextPermutation(perm));

        System.out.println(res);
    }

}
