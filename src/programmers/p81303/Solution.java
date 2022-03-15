package programmers.p81303;

import java.util.*;

public class Solution {
    static class LinkedList {
        int cnt;
        Node head = new Node(-1);
        Node curNode;
        Stack<Node> delStack = new Stack<>();

        public LinkedList(int n, int k) {
            this.cnt = n;
            curNode = head;
            for (int i = 0; i < n; i++) {
                Node newNode = new Node(i);
                curNode.next = newNode;
                newNode.prev = curNode;
                curNode = curNode.next;
            }

            curNode = head.next;
            for (int i = 0; i < k; i++) {
                curNode = curNode.next;
            }

        }

        public void delete() {
            delStack.push(curNode);

            Node prevNode = curNode.prev;
            Node nextNode = curNode.next;

            prevNode.next = curNode.next;
            if (nextNode == null) {
                curNode = prevNode;
            } else {
                nextNode.prev = prevNode;
                curNode = nextNode;
            }
        }

        public void undo() {
            Node restoreNode = delStack.pop();
            Node prevNode = restoreNode.prev;
            Node nextNode = restoreNode.next;

            prevNode.next = restoreNode;

            if (nextNode != null) {
                nextNode.prev = restoreNode;
            }
        }

        public void up(int num) {
            for (int i = 0; i < num; i++) {
                curNode = curNode.prev;
            }
        }

        public void down(int num) {
            for (int i = 0; i < num; i++) {
                curNode = curNode.next;
            }

        }

        public String print() {
            Node cur = head.next;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cnt; i++) {
                sb.append('X');
            }
            while (cur != null) {
                sb.setCharAt(cur.num, 'O');
                cur = cur.next;
            }

            return sb.toString();
        }
    }

    static class Node {
        int num;
        Node prev, next;

        public Node(int num) {
            this.num = num;
            this.prev = null;
            this.next = null;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        LinkedList linkedList = new LinkedList(n, k);

        int len = cmd.length;
        for (int i = 0; i < len; i++) {
            String[] splitCmd = cmd[i].split(" ");
            char ch = splitCmd[0].charAt(0);
            switch (ch) {
                case 'U':
                    int num = Integer.parseInt(splitCmd[1]);
                    linkedList.up(num);
                    break;
                case 'D':
                    num = Integer.parseInt(splitCmd[1]);
                    linkedList.down(num);
                    break;
                case 'C':
                    linkedList.delete();
                    break;
                case 'Z':
                    linkedList.undo();
            }
        }

        return linkedList.print();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        {
            String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
            System.out.println(s.solution(8, 2, cmd));
        }

        {
            String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
            System.out.println(s.solution(8, 2, cmd));
        }
    }
}
