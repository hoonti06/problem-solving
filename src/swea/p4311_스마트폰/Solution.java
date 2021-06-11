package swea.p4311_스마트폰;

import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static boolean[] op = new boolean[5]; // null, +, -, *, /
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
    }
}
