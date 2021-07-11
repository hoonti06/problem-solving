package boj.p12XXX.p12886;

import java.io.*;
import java.util.*;

public class Main {
    static Set<Node> set = new HashSet<>();
    static class Node {
        int a, b, c;
        Node(int a, int b, int c) {
            int[] mine = new int[]{a, b, c};
            Arrays.sort(mine);
            this.a = mine[0];
            this.b = mine[1];
            this.c = mine[2];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return a == node.a && b == node.b && c == node.c;
        }

        @Override
        public int hashCode() {
            int hash = a * 31;
            hash += (hash +  b) * 31 + 7;
            hash += (hash +  c) * 31 + 19;
            return hash;
        }
    }

    static void go(Node node) {
        if (set.contains(node)) return;
        set.add(node);

        if (node.a < node.b) {
            go(new Node(node.a + node.a, node.b - node.a, node.c));
        }
        if (node.a < node.c) {
            go(new Node(node.a + node.a, node.b, node.c - node.a));
        }
        if (node.b < node.c) {
            go(new Node(node.a, node.b + node.b, node.c - node.b));
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] split = in.readLine().split(" ");
        int A = Integer.parseInt(split[0]);
        int B = Integer.parseInt(split[1]);
        int C = Integer.parseInt(split[2]);
        int isPossible = 0;
        int sum = A + B + C;
        if (sum % 3 == 0) {
            int quot = sum / 3;
            go(new Node(A, B, C));
            isPossible = set.contains(new Node(quot, quot, quot))? 1 : 0;
        }
        System.out.println(isPossible);
    }
}
