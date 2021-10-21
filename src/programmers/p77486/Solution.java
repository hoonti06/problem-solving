package programmers.p77486;

import java.util.*;

public class Solution {
    static Map<String, String> parents;
    static Map<String, Integer> money;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        parents = new HashMap<>();
        money = new HashMap<>();

        int N = referral.length;
        for (int i = 0; i < N; i++) {
            String parent = referral[i];
            String child = enroll[i];

            parents.put(child, parent);
            money.put(child, 0);
        }

        int M = seller.length;
        for (int i = 0; i < M; i++) {
            String cur = seller[i];
            int curAmount = amount[i] * 100;
            while (true) {
                int curShare = curAmount;
                if ("-".equals(cur)) {
                    money.put(cur, money.getOrDefault(cur, 0) + curShare);
                    break;
                }

                curAmount /= 10;
                curShare -= curAmount;
                money.put(cur, money.getOrDefault(cur, 0) + curShare);
                cur = parents.get(cur);
                if (curAmount == 0) {
                    break;
                }
            }
        }

        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            res[i] = money.get(enroll[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"}, new int[]{12, 4, 2, 5, 10});

        s.solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"sam", "emily", "jaimie", "edward"}, new int[]{2, 3, 5, 4});
    }
}
