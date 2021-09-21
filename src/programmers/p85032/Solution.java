package programmers.p85032;

import java.util.*;

public class Solution {
    static class Boxer implements Comparable<Boxer> {
        int num;
        int weight;
        double percent;
        int fightCount;
        int winCount;
        int winCountBigger;

        private double getWinPercent() {
            if (fightCount == 0) {
                return 0.0;
            }

            return (double) winCount / fightCount;
        }

        @Override
        public int compareTo(Boxer o) {
            int ret = Double.compare(o.getWinPercent(), this.getWinPercent());
            if (ret != 0) {
                return ret;
            }

            if (this.winCountBigger != o.winCountBigger) {
                return o.winCountBigger - this.winCountBigger;
            }

            if (this.weight != o.weight) {
                return o.weight - this.weight;
            }

            return this.num - o.num;
        }
    }

    public int[] solution(int[] weights, String[] head2head) {
        int N = weights.length;
        int[] answer = new int[N];
        Boxer[] boxers = new Boxer[N];
        for (int i = 0; i < N; i++) {
            boxers[i] = new Boxer();
        }

        for (int i = 0; i < N; i++) {
            int weight = weights[i];
            String state = head2head[i];
            Boxer boxer = boxers[i];
            boxer.weight = weight;
            boxer.num = i;

            for (int j = 0; j < N; j++) {
                char curState = state.charAt(j);
                if (curState == 'W') {
                    boxer.fightCount++;
                    boxer.winCount++;
                    if (boxer.weight < weights[j]) {
                        boxer.winCountBigger++;
                    }
                } else if (curState == 'L') {
                    boxer.fightCount++;
                }
            }
        }

        Arrays.sort(boxers);
        for (int i = 0; i < N; i++) {
            answer[i] = boxers[i].num + 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        {
            int[] ret = s.solution(new int[]{50, 82, 75, 120}, new String[]{"NLWL", "WNLL", "LWNW", "WWLN"});
            System.out.println(Arrays.toString(ret));
        }
        {
            int[] ret = s.solution(new int[]{145,92,86}, new String[]{"NLW","WNL","LWN"});
            System.out.println(Arrays.toString(ret));
        }
        {
            int[] ret = s.solution(new int[]{60,70,60}, new String[]{"NNN","NNN","NNN"});
            System.out.println(Arrays.toString(ret));
        }
    }
}
