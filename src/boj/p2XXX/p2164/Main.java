package boj.p2XXX.p2164;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();
        int cnt = 1;
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }
        while (q.size() > 1) {

            if (cnt % 2 == 1) { // 버림
                q.poll();
            } else { // 뒤로 보내기
                q.offer(q.poll());
            }
            cnt++;
        }
        System.out.println(q.peek());
    }
}
