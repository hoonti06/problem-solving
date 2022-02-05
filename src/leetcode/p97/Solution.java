package leetcode.p97;

public class Solution {
    static boolean[][][] memo;
    public boolean isInterleave(String s1, String s2, String s3) {
        memo = new boolean[101][101][201];
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        return recur(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0);
    }

    public boolean recur(char[] s1, char[] s2, char[] s3, int i1, int i2, int i3) {
        if (memo[i1][i2][i3]) {
            return false;
        }
        memo[i1][i2][i3] = true;

        if (i3 >= s3.length) {
            return true;
        }

        boolean ret = false;
        if (i1 < s1.length && s1[i1] == s3[i3]) {
            ret = recur(s1, s2, s3, i1 + 1, i2, i3 + 1);
        }
        if (ret) return true;

        if (i2 < s2.length && s2[i2] == s3[i3]) {
            ret = recur(s1, s2, s3, i1, i2 + 1, i3 + 1);
        }

        return ret;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isInterleave("aabcc", "dbbca", "aadbbcbcac"));

        System.out.println(s.isInterleave("aabcc", "dbbca", "aadbbbaccc"));

        System.out.println(s.isInterleave("", "", ""));
    }
}
