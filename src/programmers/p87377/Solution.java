package programmers.p87377;

import java.util.*;

public class Solution {
    public String[] solution(int[][] line) {
        long minx = Long.MAX_VALUE;
        long miny = Long.MAX_VALUE;
        long maxx = Long.MIN_VALUE;
        long maxy = Long.MIN_VALUE;
        List<Long> xlist = new ArrayList<>();
        List<Long> ylist = new ArrayList<>();

        int N = line.length;
        for (int i = 0; i < N; i++) {
            int A = line[i][0];
            int B = line[i][1];
            int E = line[i][2];
            for (int j = i+1; j < N; j++) {
                int C = line[j][0];
                int D = line[j][1];
                int F = line[j][2];

                long div = (long)A * D - (long)B * C;
                if (div == 0) {
                    continue;
                }

                long xx = (long)B * F - (long)E * D;
                if (xx % div != 0) {
                    continue;
                }

                long yy = (long)E * C - (long)A * F;
                if (yy % div != 0) {
                    continue;
                }

                long x = xx / div;
                long y = yy / div;

                minx = Math.min(minx, x);
                maxx = Math.max(maxx, x);
                miny = Math.min(miny, y);
                maxy = Math.max(maxy, y);

                xlist.add(x);
                ylist.add(y);
            }
        }

        long R = maxy - miny + 1;
        long C = maxx - minx + 1;
        Set<Long> set = new HashSet<>();

        int m = xlist.size();
        for (int i = 0; i < m; i++) {
            long x = xlist.get(i);
            long y = ylist.get(i);

            long r = (maxy - y);
            long c = x - minx;

            set.add(r * C + c);
        }

        String[] answer = new String[(int)R];
        for (int i = 0; i < R; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < C; j++) {
                if (set.contains(i * C + j)) {
                    sb.append("*");
                } else {
                    sb.append(".");
                }
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}