package boj.p2XXX.p2526_싸이클;

import java.io.FileInputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int P = sc.nextInt();
        int cur = N;

//        int order = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 1;
        map.put(N, 1);
        while (true) {
            cur = (cur * N) % P;
            if (map.containsKey(cur)) {
                cnt -= map.get(cur)-1;
                break;
            }
            map.put(cur, ++cnt);
        }
        System.out.println(cnt);
    }
}
