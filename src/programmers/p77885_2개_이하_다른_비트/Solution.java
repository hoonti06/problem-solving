package programmers.p77885_2개_이하_다른_비트;

import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        int len = numbers.length;
        long[] answer = new long[len];

        for (int i = 0; i < len; i++) {
            long num = numbers[i];
            char[] origin = new char[60];
            Arrays.fill(origin, '0');
            char[] dd = Long.toBinaryString(num).toCharArray();
            for (int ii = 59, ll = dd.length-1; ll >= 0; ll--, ii--)
                origin[ii] = dd[ll];
            for (int j = origin.length-1; j >= 0; j--) {
                if (origin[j] == '0') {
                    long a = (long) Math.pow(2, origin.length-j - 1);
                    num += a;
                    if (j != origin.length-1) {
                        num -= (a / 2);
                    }
                    break;
                }
            }
            answer[i] = num;
        }
        return answer;
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new long[]{2, 7});
        s.solution(new long[]{7});
    }
}
