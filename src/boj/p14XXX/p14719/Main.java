package boj.p14XXX.p14719;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static Scanner sc;
    static String str;

    static Stack<Character> stack = new Stack<>();
    static Stack<Integer> stmul = new Stack<>();
    static Stack<Integer> st = new Stack<>();

//    int dfs(int idx) {
//
//    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));

        sc = new Scanner(System.in);
        str = sc.next();
//        int res = solution();
//        System.out.println(res);
    }
}
