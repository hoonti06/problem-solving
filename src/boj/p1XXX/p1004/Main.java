package boj.p1XXX.p1004;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(in.readLine());
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                int rSquare = r * r;

                int sxDiff = sx - x;
                int syDiff = sy - y;
                int startDistSquare = sxDiff * sxDiff + syDiff * syDiff;

                int exDiff = ex - x;
                int eyDiff = ey - y;
                int endDistSquare = exDiff * exDiff + eyDiff * eyDiff;

                boolean startIn = startDistSquare < rSquare;
                boolean endIn = endDistSquare < rSquare;
                if ((startIn && !endIn) || (!startIn && endIn)) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
