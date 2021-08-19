package boj.p1XXX.p1918;

import java.io.*;
import java.util.*;

public class Main {
    static String infix;

    static class Node {
        int priority;
        char ch;

        Node(char ch, int priority) {
            this.ch = ch;
            this.priority = priority;
        }
    }


    static String solution() {
        StringBuilder postfix = new StringBuilder();
        Deque<Node> opStack = new ArrayDeque<>();
        int braketCnt = 0;

        int len = infix.length();
        for (int i = 0; i < len; i++) {
            char curCh = infix.charAt(i);
            if (Character.isUpperCase(curCh)) {
                postfix.append(curCh);
            }
            else {
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

        return postfix.toString();
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);

        infix = sc.next();

        System.out.println(solution());
    }
}
