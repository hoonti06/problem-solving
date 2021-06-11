package boj.p6XXX.p6198_옥상정원_꾸미기;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static class Node {
        int num;
        long popCnt;
        Node (int num, long popCnt) {
            this.num = num;
            this.popCnt = popCnt;
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        Deque<Node> stack = new ArrayDeque<>();

        long res = 0;
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(in.readLine());
            long popSum = 0;
            while (!stack.isEmpty()) {
                Node topNode = stack.peek();
                int top = topNode.num;
                long popCnt = topNode.popCnt;
                if (top <= cur) {
                    stack.pop();
                    popSum += popCnt;
                    res += popSum;
                    System.out.println(popSum);
                    popSum++;
//                    res += popCnt + 1;
//                    res += popSum;
//                    popSum += topNode.popCnt + 1;
//                    popSum += popCnt;
//                    System.out.println(popSum);
//                    res += popSum;
//                    popSum++;
                } else {
                    break;
                }
            }
            if (!stack.isEmpty()) {
                Node topNode = stack.pop();
                topNode.popCnt = popSum;
                stack.push(topNode);
            }
            stack.push(new Node(cur, 0));
        }

        long popSum = 0;
        while (!stack.isEmpty()) {
            Node topNode = stack.pop();
            res += popSum;
            popSum += topNode.popCnt + 1;
        }
        System.out.println(res);
    }
}
