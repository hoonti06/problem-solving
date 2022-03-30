package programmers.kakao2019winterintern.p64064;

import java.util.*;

public class Solution {
    static int N, M;
    static boolean[][] matched;
    static Set<Integer> visitedBits;
    static int res;

    public int solution(String[] user_id, String[] banned_id) {
        res = 0;
        N = banned_id.length;
        M = user_id.length;
        matched = new boolean[N][M];
        visitedBits = new HashSet<>();

        for (int i = 0; i < N; i++) {
            char[] banIdChars = banned_id[i].toCharArray();
            for (int j = 0; j < M; j++) {
                if (banIdChars.length != user_id[j].length()) {
                    continue;
                }
                char[] uIdChars = user_id[j].toCharArray();
                boolean isMatched = true;
                for (int k = 0; k < uIdChars.length; k++) {
                    if (banIdChars[k] != '*' && banIdChars[k] != uIdChars[k]) {
                        isMatched = false;
                        break;
                    }
                }
                if (isMatched) {
                    matched[i][j] = true;
                }
            }
        }

        go(0, 0);
        System.out.println(res);
        return res;
    }

    public void go(int idx, int bit) {
        if (idx >= N) {
            res++;
            return;
        }

        for (int j = 0; j < M; j++) {
            if (!matched[idx][j]) continue;
            if ((bit & (1 << j)) > 0) {
                continue;
            }
            int nextBit = bit | 1 << j;
            if (visitedBits.contains(nextBit)) {
                continue;
            }
            visitedBits.add(nextBit);
            go(idx + 1, nextBit);
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "abc1**"});
        s.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"*rodo", "*rodo", "******"});
        s.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "*rodo", "******", "******"});
    }

}
