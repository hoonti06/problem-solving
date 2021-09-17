package programmers.p83201;

import java.util.*;

public class Solution {
    public String solution(int[][] scores) {
        StringBuilder answer = new StringBuilder();
        int N = scores.length;
        for (int j = 0; j < N; j++) {
            Map<Integer, Integer> scoreCnts = new HashMap<>();
            int max = 0;
            int min = 100000000;
            int sum = 0;
            int selfScore = 0;
            int div = N;
            for (int i = 0; i < N; i++) {
                int curScore = scores[i][j];
                if (i == j) {
                    selfScore = curScore;
                }
                int cnt = scoreCnts.getOrDefault(curScore, 0) + 1;
                scoreCnts.put(curScore, cnt);
                max = Math.max(max, curScore);
                min = Math.min(min, curScore);
                sum += curScore;
            }
            if (max == selfScore || min == selfScore) {
                if (scoreCnts.get(selfScore) == 1) {
                    sum -= selfScore;
                    div--;
                }
            }
            answer.append(getGrade((double) sum / div));
        }
        return answer.toString();
    }

    char getGrade(double score) {
        if (Double.compare(score, 90.0) >= 0) {
            return 'A';
        } else if (Double.compare(score, 80.0) >= 0) {
            return 'B';
        } else if (Double.compare(score, 70.0) >= 0) {
            return 'C';
        } else if (Double.compare(score, 50.0) >= 0) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        {
            String ret = s.solution(new int[][]{{100, 90, 98, 88, 65}, {50, 45, 99, 85, 77}, {47, 88, 95, 80, 67}, {61, 57, 100, 80, 65}, {24, 90, 94, 75, 65}});
            System.out.println(ret); // FBABD
        }
        {
            String ret = s.solution(new int[][]{{50, 90}, {50, 87}});
            System.out.println(ret); // DA
        }
        {
            String ret = s.solution(new int[][]{{70, 49, 90}, {68, 50, 38}, {73, 31, 100}});
            System.out.println(ret); // CFD
        }
    }

}
