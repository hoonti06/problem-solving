package programmers.kakao2022.p92342;

public class Solution {
    static int maxDiff;
    static int[] ryan = new int[11];
    static int[] apeach = new int[11];
    static int[] res = new int[11];

    public int[] solution(int n, int[] info) {
        maxDiff = 0;
        System.arraycopy(info, 0, apeach, 0, info.length);

        go(0, n);

        if (maxDiff == -1) {
            return new int[]{-1};
        }
        return res;
    }

    public void go(int idx, int remain) {
        if (idx >= 11 || remain <= 0) {
            ryan[10] += remain;
            calScore();
            ryan[10] -= remain;
            return;
        }

        if (apeach[idx] < remain) {
            int curScore = apeach[idx] + 1;
            ryan[idx] += curScore;
            go(idx + 1, remain - curScore);
            ryan[idx] -= curScore;
        }

        go(idx + 1, remain);
    }

    public void calScore() {
        int ryanScore = 0, apeachScore = 0;
        for (int i = 0, curScore = 10; i < 11; i++, curScore--) {
            if(ryan[i] > apeach[i]) {
                ryanScore += curScore;
            } else if(apeach[i] > 0) {
                apeachScore += curScore;
            }
        }

        int diff = ryanScore - apeachScore;
        if(diff > 0 && maxDiff <= diff) {
            if(maxDiff == diff && !isBetter()) {
                return;
            }
            maxDiff = diff;
            System.arraycopy(ryan, 0, res, 0, ryan.length);
        }
    }

    public boolean isBetter() {
        for(int i = 10; i >= 0; i--) {
            if (ryan[i] > res[i]) {
                return true;
            } else if (ryan[i] < res[i]) {
                return false;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0});
        s.solution(1, new int[]{1,0,0,0,0,0,0,0,0,0,0});
        s.solution(9, new int[]{0,0,1,2,0,1,1,1,1,1,1});
        s.solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3});
    }
}
