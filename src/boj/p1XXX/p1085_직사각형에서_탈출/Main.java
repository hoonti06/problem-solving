package boj.p1XXX.p1085_직사각형에서_탈출;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();

        int minWidth = Math.min(x, w-x);
        int minHeight = Math.min(y, h-y);
        System.out.println(Math.min(minWidth, minHeight));
    }
}