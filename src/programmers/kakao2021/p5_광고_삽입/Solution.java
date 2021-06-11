package programmers.kakao2021.p5_광고_삽입;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public int stringToSec(String str) {
        String[] splitted = str.split(":");
        int hour = Integer.parseInt(splitted[0]);
        int min = Integer.parseInt(splitted[1]);
        int sec = Integer.parseInt(splitted[2]);

        int ret = hour * 3600;
        ret += min * 60;
        ret += sec;
        return ret;
    }

    public String secToString(int num) {
        int sec = num % 60; num /= 60;
        int min = num % 60; num /= 60;
        int hour = num;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", hour)).append(":")
            .append(String.format("%02d", min)).append(":")
            .append(String.format("%02d", sec));

        return sb.toString();
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        final int MAX = 100 * 3600;
        int[] cnt = new int[MAX];
        int N = stringToSec(play_time);
        int M = stringToSec(adv_time);

        for (String log : logs) {
            String[] curr = log.split("-");
            int start = stringToSec(curr[0]);
            int end = stringToSec(curr[1]);
            for(int i = start; i < end; i++) cnt[i]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        long sum = 0;
        for (int i = 0; i < M; i++) sum += cnt[i];

        long maxSum = sum;
        int idx = 0;
        for (int i = M, prev = 0; i < N; i++, prev++) {
            sum += cnt[i] - cnt[prev];
            if(maxSum < sum) {
                idx = prev+1;
                maxSum = sum;
            }
        }
        String answer = secToString(idx);
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29",
                "01:30:59-01:53:29", "01:37:44-02:02:30"};
        s.solution("02:03:55", "00:14:15", logs);
        // "01:30:59"

//        String[] logs2 = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
//        s.solution("99:59:59", "25:00:00", logs2);
        // "01:00:00"

//        String[] logs3 = {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"};
//        s.solution("50:00:00", "50:00:00", logs3);
        // "00:00:00"
    }
}
