package boj.p2XXX.p2564_경비원;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer> stores = new ArrayList<>();
    static int N, M, T;
    static final int NORTH = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;
    static final int EAST = 4;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        T = Integer.parseInt(in.readLine());

        for (int i = 0; i <= T; i++) {
            st = new StringTokenizer(in.readLine(), " ");

            int dir = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            switch (dir) {
                case NORTH:
                    stores.add(N + len);
                    break;
                case SOUTH:
                    stores.add(-len);
                    break;
                case WEST:
                    stores.add(N - len);
                    break;
                case EAST:
                    stores.add(N + M + len);
                    break;
            }
        }

        int a = stores.get(T);
        int sum = 0;
        int total = (M + N) * 2;
        for (int i = 0; i < T; i++) {
            int dist = Math.abs(a - stores.get(i));
            sum += Math.min(dist, total - dist);
        }
        System.out.println(sum);
    }
}
