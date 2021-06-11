package boj.p1XXX.p1759_암호만들기;

import java.io.FileInputStream;
import java.util.*;

public class Main {
    static int R, N;
    static final char[] moem = {'a', 'e', 'i', 'o', 'u'};
    static char[] input;
    static int[] perm;
    static List<String> res = new ArrayList<>();

    static void solution() {
        Arrays.sort(input);
        do {
//
            StringBuilder sb = new StringBuilder();
            int moemCnt = 0;
            for (int i = 0; i < N; i++) {
                if (perm[i] == 0) continue;
                char curCh = input[i];
                sb.append(curCh);
                for (int j = 0; j < 5; j++) {
                    if (curCh == moem[j]) {
                        moemCnt++;
                        break;
                    }
                }
            }

            if (moemCnt >= 1 && (R - moemCnt) >= 2)
                res.add(sb.toString());
        } while (nextPermutation());
    }

    static boolean nextPermutation() {
        int i = N - 1;
        while (i - 1 >= 0 && perm[i - 1] >= perm[i]) i--;
        if (i <= 0) return false;

        int j = N - 1;
        while (perm[i - 1] >= perm[j]) j--;

        int tmp = perm[i - 1];
        perm[i - 1] = perm[j];
        perm[j] = tmp;

        int k = N - 1;
        while (i < k) {
            tmp = perm[i];
            perm[i] = perm[k];
            perm[k] = tmp;
            i++;
            k--;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        N = sc.nextInt();
        input = new char[N];
        perm = new int[N];
        for (int i = 0; i < R; i++) perm[i] = 1;
        Arrays.sort(perm);

        for (int i = 0; i < N; i++)
            input[i] = sc.next().charAt(0);

        solution();

        Collections.sort(res);
        for (int i = 0; i < res.size(); i++)
            System.out.println(res.get(i));
    }
}
