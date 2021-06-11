package boj.p7XXX.p7662_이중우선순위큐;

import java.io.*;
import java.util.*;

public class Main {

    static int N, size;
    static boolean[] deleted;
    static PriorityQueue<Node> minHeap, maxHeap;

    static class Node {
        int idx;
        int num;
        Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    static void solution(int idx, char cmd, int num) {
        if (cmd == 'D') {
            if (size == 0) {
                minHeap.clear();
                maxHeap.clear();
                return;
            }

            if (num == 1) { // max
                while (true) {
                    Node del = maxHeap.poll();
                    if (!deleted[del.idx]) {
                        deleted[del.idx] = true;
                        break;
                    }
                }
            } else { // min
                while (true) {
                    Node del = minHeap.poll();
                    if (!deleted[del.idx]) {
                        deleted[del.idx] = true;
                        break;
                    }
                }
            }
            size--;
        } else {
            minHeap.add(new Node(idx, num));
            maxHeap.add(new Node(idx, num));
            size++;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < testcase; tc++) {
            minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.num));
            maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.num, a.num));
            size = 0;

            N = Integer.parseInt(in.readLine());
            deleted = new boolean[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                char cmd = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());
                solution(i, cmd, num);
            }

            if (size > 0) {
                while (!minHeap.isEmpty() && deleted[minHeap.peek().idx])
                    minHeap.poll();
                while (!maxHeap.isEmpty() && deleted[maxHeap.peek().idx])
                    maxHeap.poll();

                sb.append(maxHeap.peek().num).append(" ").append(minHeap.peek().num);
            } else
                sb.append("EMPTY");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}