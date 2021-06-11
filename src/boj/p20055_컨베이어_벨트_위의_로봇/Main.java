package boj.p20055_컨베이어_벨트_위의_로봇;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int weight;
        boolean robotOn;

        Node(int weight, boolean robotOn) {
            this.weight = weight;
            this.robotOn = robotOn;
        }
    }

    static int N, K;
    static List<Node> overList = new ArrayList<>();
    static List<Node> underList = new ArrayList<>();

    static int calZeroCount() {
        int cnt = 0;
        for (Node e : overList)
            if (e.weight == 0) cnt++;
        for (Node e : underList)
            if (e.weight == 0) cnt++;

        return cnt;
    }

    static void moveBelt() {
        Node last = overList.remove(overList.size() - 1);
        last.robotOn = false;
        underList.add(0, last);

        last = underList.remove(underList.size() - 1);
        overList.add(0, last);
    }

    static void moveRobot() {
        overList.get(N - 1).robotOn = false;

        for (int i = N - 1; i > 0; i--) {
            if (overList.get(i).robotOn == true || overList.get(i - 1).robotOn == false)
                continue;
            if (overList.get(i).weight <= 0)
                continue;

            overList.get(i).robotOn = true;
            overList.get(i - 1).robotOn = false;
            overList.get(i).weight--;
        }
    }

    static void liftRobot() {
        Node first = overList.get(0);
        if (first.weight > 0) {
            first.weight--;
            first.robotOn = true;
        }
    }

    static int solution() {
        int level = 0;
        while (true) {
            if (calZeroCount() >= K) break;

            moveBelt();
            moveRobot();
            liftRobot();
            level++;
        }
        return level;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = in.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);
        K = Integer.parseInt(firstLine[1]);
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < N; i++)
            overList.add(new Node(Integer.parseInt(st.nextToken()), false));
        for (int i = 0; i < N; i++)
            underList.add(new Node(Integer.parseInt(st.nextToken()), false));

        System.out.println(solution());
    }
}
