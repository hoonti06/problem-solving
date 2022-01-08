package programmers.p17676;

public class Solution {
    public int solution(String[] lines) {
        int n = lines.length;
        long[] startTimestamp = new long[n];
        long[] endTimestamp = new long[n];
        for (int i = 0; i < n; i++) {
            String line = lines[i].substring(11);
            long[] ret = calTimestamps(line);
            startTimestamp[i] = ret[0];
            endTimestamp[i] = ret[1];
        }

        int res = 1;
        for (int i = 0; i < n; i++) {
            long after1Sec = endTimestamp[i] + (1000 - 1);
            int cnt = 1;
            for (int j = i+1; j < n; j++) {
                if (endTimestamp[j] <= after1Sec) {
                    cnt++;
                } else if (startTimestamp[j] <= after1Sec) {
                    cnt++;
                }
            }
            res = Math.max(res, cnt);
        }
        return res;
    }

    public long[] calTimestamps(String str) {
        long endTimestamp = 0L;

        int milliSec = Integer.parseInt(str.substring(9, 12));
        long unitConversionFactor = 1;
        endTimestamp += milliSec;

        int sec = Integer.parseInt(str.substring(6, 8));
        unitConversionFactor *= 1000;
        endTimestamp += sec * unitConversionFactor;

        int min = Integer.parseInt(str.substring(3, 5));
        unitConversionFactor *= 60;
        endTimestamp += min * unitConversionFactor;

        int hour = Integer.parseInt(str.substring(0, 2));
        unitConversionFactor *= 60;
        endTimestamp += hour * unitConversionFactor;

        long period = (long) (Double.parseDouble(str.substring(13, str.length() - 1)) * 1000);
        long startTimestamp = endTimestamp - period + 1;
        return new long[]{startTimestamp, endTimestamp};
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[]{"2016-09-15 00:00:00.000 3s"})); // 1
        System.out.println(s.solution(new String[]{"2016-09-15 23:59:59.999 0.001s"})); // 1
        System.out.println(s.solution(new String[]{"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"})); // 1
        System.out.println(s.solution(new String[]{"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"})); // 2
        System.out.println(s.solution(new String[]{"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"})); // 7
        System.out.println(s.solution(new String[]{"2016-09-15 00:00:00.000 2.3s", "2016-09-15 23:59:59.999 0.1s"})); // 1
    }
}
