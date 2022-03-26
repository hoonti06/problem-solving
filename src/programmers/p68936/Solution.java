package programmers.p68936;

import java.util.*;

public class Solution {
    static int[] res;
    static final int NOT_SAME = 2;

    public int[] solution(int[][] arr) {
        res = new int[3];
        int ret = go(arr, 0, 0, arr.length);
        res[ret]++;

        return new int[]{res[0], res[1]};
    }

    public int go(int[][] arr, int sr, int sc, int size) {
        if (size <= 1) {
            return arr[sr][sc];
        }

        int nextSize = size / 2;

        int[][] ret = new int[2][2];
        for (int r = sr, i = 0; i < 2; r += nextSize, i++) {
            for (int c = sc, j = 0; j < 2; c += nextSize, j++) {
                ret[i][j] = go(arr, r, c, nextSize);
            }
        }
        boolean isSame = true;
        int ref = ret[0][0];
        for (int i = 0; i < 2 && isSame; i++) {
            for (int j = 0; j < 2; j++) {
                if (ret[i][j] != ref) {
                    isSame = false;
                    break;
                }
            }
        }

        if (isSame) {
            return ref;
        } else {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    res[ret[i][j]]++;
                }
            }
            return NOT_SAME;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[][]{{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}});
        s.solution(new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1}, {0, 1, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 1, 1}});
    }

}
