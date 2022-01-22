package programmers.p67257;

import java.util.*;


public class Solution {
    static List<Long> operands;
    static List<Character> operators;
    static Map<Character, Integer> opPriority;
    static long res;
    static final char[] op = new char[]{'-', '+', '*'};

    public long solution(String expression) {
        init(expression);
        priorityPermutation(0);
        return res;
    }

    public void init(String expression) {
        res = 0L;
        opPriority = new HashMap<>();
        operands = new ArrayList<>();
        operators = new ArrayList<>();

        char[] str = expression.toCharArray();
        int n = str.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(str[i])) {
                operands.add(Long.parseLong(sb.toString()));
                operators.add(str[i]);
                sb.setLength(0);
            } else {
                sb.append(str[i]);
            }
        }
        operands.add(Long.parseLong(sb.toString()));
    }

    public void priorityPermutation(int idx) {
        if (idx >= 3) {
            res = Math.max(res, Math.abs(calculate()));
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (opPriority.containsKey(op[i])) {
                continue;
            }
            opPriority.put(op[i], idx);
            priorityPermutation(idx + 1);
            opPriority.remove(op[i]);
        }
    }

    public long calculate() {
        ArrayDeque<Long> operandStack = new ArrayDeque<>();
        ArrayDeque<Character> opStack = new ArrayDeque<>();

        int n = operators.size();
        for (int i = 0; i < n; i++) {
            char curOp = operators.get(i);
            operandStack.push(operands.get(i));
            while (!opStack.isEmpty()) {
                char topOp = opStack.getFirst();
                if (opPriority.get(curOp) < opPriority.get(topOp)) {
                    opStack.push(topOp);
                    break;
                }

                long second = operandStack.pop();
                long first = operandStack.pop();
                if (topOp == '-') {
                    operandStack.push(first - second);
                } else if (topOp == '+') {
                    operandStack.push(first + second);
                } else {
                    operandStack.push(first * second);
                }
            }
            opStack.push(curOp);
        }

        operandStack.push(operands.get(n));

        while (!opStack.isEmpty()) {
            char topOp = opStack.pop();

            long second = operandStack.pop();
            long first = operandStack.pop();

            long ret = operate(first, topOp, second);
            operandStack.push(ret);
        }
        return operandStack.pop();
    }

    public long operate(long first, char op, long second) {
        if (op == '-') {
            return first - second;
        } else if (op == '+') {
            return first + second;
        } else {
            return first * second;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("100-200*300-500+20")); // 60420
        System.out.println(s.solution("50*6-3*2")); // 300
    }
}
