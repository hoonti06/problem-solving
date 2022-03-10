package boj.p21943;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final char[] operators = {'+', '*'};
    static int[] operandCnt = new int[10];
    static int[] opCnt = new int[2];
    static char[] opOrder;
    static int[] operandOrder;
    static Set<String> visited;
    static final Map<Character, Integer> opPriority = new HashMap<>();
    static {
        opPriority.put('+', 0);
        opPriority.put('*', 1);
    }

    static int go(int idx, String str) {
        if (visited.contains(str)) {
            return 0;
        }

        visited.add(str);
        int rem = idx % 2;
        if (idx >= 3 && rem == 1) {
            int len = str.length();
            // 연산자를 가운데 두고 양 옆의 정수를 서로 교환한 대상도 방문 처리
            String newStr = str.substring(0, len - 3) + str.charAt(len - 1)
                    + str.charAt(len - 2) + str.charAt(len - 3);
            visited.add(newStr);
        }

        if (idx >= N-1 + N) {
            return calculate();
        }

        int res = 0;
        int halfIdx = idx / 2;
        if (rem == 0) {
            int len = operandCnt.length;
            for (int i = 1; i < len; i++) {
                if (operandCnt[i] <= 0) {
                    continue;
                }
                operandCnt[i]--;
                operandOrder[halfIdx] = i;
                int ret = go(idx + 1, str + operandOrder[halfIdx]);
                res = Math.max(res, ret);
                operandCnt[i]++;
            }
        } else {
            int len = operators.length;
            for (int i = 0; i < len; i++) {
                if (opCnt[i] <= 0) {
                    continue;
                }
                opCnt[i]--;
                opOrder[halfIdx] = operators[i];
                int ret  = go(idx + 1, str + opOrder[halfIdx]);
                res = Math.max(res, ret);
                opCnt[i]++;
            }
        }
        return res;
    }

    static int calculate() {
        ArrayDeque<Integer> operandStack = new ArrayDeque<>();
        ArrayDeque<Character> opStack = new ArrayDeque<>();

        for (int i = 0; i < N-1; i++) {
            operandStack.push(operandOrder[i]);
            char curOp = opOrder[i];
            while (!opStack.isEmpty()) {
                char topOp = opStack.pop();
                if (opPriority.get(curOp) < opPriority.get(topOp)) {
                    opStack.push(topOp);
                    break;
                }

                int second = operandStack.pop();
                int first = operandStack.pop();
                if (topOp == '+') {
                    operandStack.push(first + second);
                } else {
                    operandStack.push(first * second);
                }
            }
            opStack.push(curOp);
        }

        operandStack.push(operandOrder[N-1]);

        while (!opStack.isEmpty()) {
            char topOp = opStack.pop();
            int second = operandStack.pop();
            int first = operandStack.pop();
            if (topOp == '+') {
                operandStack.push(first + second);
            } else {
                operandStack.push(first * second);
            }
        }
        return operandStack.pop();
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        visited = new HashSet<>();

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        operandOrder = new int[N];
        opOrder = new char[N-1];
        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int operand = Integer.parseInt(st.nextToken());
            operandCnt[operand]++;
        }
        st = new StringTokenizer(in.readLine(), " ");
        opCnt[0] = Integer.parseInt(st.nextToken());
        opCnt[1] = Integer.parseInt(st.nextToken());

        System.out.println(go(0, ""));
    }
}
