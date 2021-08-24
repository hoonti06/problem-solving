package boj.p1XXX.p1013;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String str;
    static int len;

    static void go(int idx, char state) {
        if (state == 'X') {
            System.out.println("NO");
            return;
        }

        if (idx >= len) {
            if (state == 'E' || state == 'G' || state == 'F' || state == 'S') {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
            return;
        }

        if (state == 'S') {
            if (str.charAt(idx) == '0')
                go(idx+1, 'B');
            else
                go(idx+1, 'A');
        } else if (state == 'A') {
            if (str.charAt(idx) == '0')
                go(idx+1, 'C');
            else {
                go(idx+1, 'X');
            }
        }
        else if (state == 'B') {
            if (str.charAt(idx) == '0')
                go(idx+1, 'X');
            else
                go(idx+1, 'F');
        }
        else if (state == 'C') {
            if (str.charAt(idx) == '0')
                go(idx+1, 'D');
            else
                go(idx+1, 'X');
        }
        else if (state == 'D') {
            if (str.charAt(idx) == '0')
                go(idx+1, 'D');
            else
                go(idx+1, 'E');
        }
        else if (state == 'E') {
            if (str.charAt(idx) == '1')
                go(idx+1, 'G');
            else
                go(idx+1, 'B');
        }
        else if (state == 'F') {
            go(idx, 'S');
        }
        else if (state == 'G') {
            if (str.charAt(idx) == '0')
                go(idx+1, 'H');
            else
                go(idx+1, 'G');
        }
        else if (state == 'H') {
            if (str.charAt(idx) == '1')
                go(idx+1, 'F');
            else
                go(idx+1, 'D');
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            str = in.readLine();
            len = str.length();
            go(0, 'S');
        }
    }
}
