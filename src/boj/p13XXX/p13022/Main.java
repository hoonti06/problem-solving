package boj.p13XXX.p13022;

import java.io.*;
import java.util.*;

public class Main {
    static Queue<Character> q;

    public static int solution() {
        while (!q.isEmpty()) {
            int wCnt = 0;
            while (!q.isEmpty()) {
                int ch = q.peek();
                if (ch == 'w') {
                    wCnt++;
                    q.poll();
                } else {
                    break;
                }
            }
            if (q.isEmpty() || wCnt == 0) {
                return 0;
            }
            if (isCountDifferent(wCnt, 'o')) {
                return 0;
            }
            if (isCountDifferent(wCnt, 'l')) {
                return 0;
            }
            if (isCountDifferent(wCnt, 'f')) {
                return 0;
            }
        }
        return 1;
    }

    public static boolean isCountDifferent(int cntRef, char chRef) {
        int curCnt = 0;
        while (!q.isEmpty()) {
            int ch = q.peek();
            if (ch == chRef) {
                curCnt++;
                q.poll();
            } else {
                break;
            }
        }
        return curCnt != cntRef;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        q = new ArrayDeque<>();
        char[] chars = in.readLine().toCharArray();
        for (char ch : chars) {
            q.offer(ch);
        }
        System.out.println(solution());
    }
}
