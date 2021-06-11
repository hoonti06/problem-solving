package boj.p1XXX.p1013;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static Scanner sc;
    static int T;
//    String str;

    public static boolean solution(String s) {
        int i = 0;
        int length = s.length();
        boolean running = true;
        while (i < length) {
            int j = i;
            while (j < length) {
//                if (j +1 < length && s.substring(j, 2).equals("01"))
                if (s.charAt(j) == '0') {
                    if (j + 1 < length && s.charAt(j + 1) == '1') {
                        j += 2;
                        break;
                    } else {
                        running = false;
                        break;
                    }
                } else {
                    if (j + 2 < length) {
                        if (s.substring(j, j + 2).equals("100")) {
                            j += 3;
                            while (j < length) {
                                if (j + 1 < length && s.substring(j, 2).equals("10")) {
                                    j++;
                                    break;
                                } else if (j + 1 == length - 1 && s.charAt(j + 1) == '1') {

                                } else {
                                    if (j == 0)
                                        j++;
                                }
                            }
                            if (j >= length) {

                            }
                        } else {
                            running = false;
                            break;
                        }
                    } else {
                        running = false;
                        break;
                    }

                }
            }
            if (!running)
                break;
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        sc = new Scanner(System.in);

        T = sc.nextInt();
        for (int tc = 1; tc < T; tc++) {
            String str = sc.next();
            boolean res = solution(str);

        }
    }
}
