package boj.p2XXX.p2263_트리의순회;

import java.io.*;
import java.util.*;

public class Main {
    static int[] post, inPos;
    static StringBuilder sb;

    static void go(int iStart, int iEnd, int pStart, int pEnd) {
        if (iStart > iEnd || pStart > pEnd) return;

        int rootNum = post[pEnd];
        int inRoot = inPos[rootNum];

        int leftSize = inRoot - iStart;
        int pRightStart = pStart + leftSize;

        sb.append(rootNum).append(" ");
        go(iStart, inRoot-1, pStart, pRightStart - 1);
        go(inRoot + 1, iEnd, pRightStart, pEnd - 1);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        post = new int[N];
        inPos = new int[N+1];

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            inPos[num] = i;
        }

        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++)
            post[i] = Integer.parseInt(st.nextToken());

        go(0, N-1, 0, N-1);

        System.out.println(sb);
    }
}
