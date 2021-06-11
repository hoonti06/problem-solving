package boj.p1XXX.p1918;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static String infix;
    static StringBuilder postfix = new StringBuilder();
    static Stack<Node> opStack = new Stack<>();
    static HashMap<Character, Integer> map = new HashMap<>();
    //    static HashMap<Character, Integer> braketCnt = new HashMap<>();
    static int braketCnt;
//    static {
//        map.put('(', 1);
//        map.put(')', 2);
//        map.put('*', 2);
//        map.put('/', 2);
//        map.put('+', 3);
//        map.put('-', 3);
//    }

    static class Node {
        int priority;
        char ch;

        Node(char ch, int priority) {
            this.ch = ch;
            this.priority = priority;
        }
    }


    static void solution() {
        int len = infix.length();
        for (int i = 0; i < len; i++) {
            char curCh = infix.charAt(i);
//            int curNum = curCh - '0';
            if ('A' <= curCh && curCh <= 'Z') {
                postfix.append(curCh);
            } else {
                int priority;
                switch (curCh) {
                    case '(':
                        braketCnt++;
                        break;
                    case ')':
                        braketCnt--;
                        break;
                    case '*':
                    case '/':
                        priority = braketCnt * 10 + 2;
                        while (!opStack.isEmpty()) {
                            Node top = opStack.peek();
                            if (top.priority >= priority) {
                                postfix.append(opStack.pop().ch);
                            } else {
                                break;
                            }
                        }
                        opStack.push(new Node(curCh, priority));
                        break;
                    case '+':
                    case '-':
                        priority = braketCnt * 10 + 1;
                        while (!opStack.isEmpty()) {
                            Node top = opStack.peek();
                            if (top.priority >= priority) {
                                postfix.append(opStack.pop().ch);
                            } else {
                                break;
                            }
                        }
                        opStack.push(new Node(curCh, priority));
                        break;
                }
            }
        }
        while (!opStack.isEmpty())
            postfix.append(opStack.pop().ch);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);

        infix = sc.next();

        solution();
        System.out.println(postfix);
    }
}
