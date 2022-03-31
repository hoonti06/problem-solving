package programmers.kakao2018.p17681;

import java.util.*;

public class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], ' ');
        }

        for (int i = 0; i < n; i++) {
            char[] chars = Integer.toBinaryString(arr1[i]).toCharArray();
            int m = chars.length;
            for (int j = m-1, k = n-1; j >= 0; j--, k--) {
                if (chars[j] == '1') {
                    board[i][k] = '#';
                }
            }

            chars = Integer.toBinaryString(arr2[i]).toCharArray();
            m = chars.length;
            for (int j = m-1, k = n-1; j >= 0; j--, k--) {
                if (chars[j] == '1') {
                    board[i][k] = '#';
                }
            }
        }

        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            res[i] = new String(board[i]);
        }
        return res;
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
        s.solution(6, new int[]{46, 33, 33 ,22, 31, 50}, new int[]{27 ,56, 19, 14, 14, 10});
    }
}
