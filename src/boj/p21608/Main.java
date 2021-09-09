package boj.p21608;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static Set[] likes;
    static Set<Integer> existingStudents;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0,-1, 0};

    static class Seat implements Comparable<Seat> {
        int r;
        int c;
        int likeCnt;
        int emptyCnt;

        Seat(int r, int c, int likeCnt, int emptyCnt) {
            this.r = r;
            this.c = c;
            this.likeCnt = likeCnt;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public int compareTo(Seat o) {
            if (this.likeCnt != o.likeCnt) {
                return o.likeCnt - this.likeCnt;
            }
            if (this.emptyCnt != o.emptyCnt) {
                return o.emptyCnt - this.emptyCnt;
            }
            if (this.r != o.r) {
                return this.r - o.r;
            }
            return this.c - o.c;
        }
    }

    static void solution(int student) {
        List<Seat> possibleSeats = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    continue;
                }
                int likeCnt = 0;
                int emptyCnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dx[d];
                    int nc = j + dy[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }

                    int seatingStudent = board[nr][nc];
                    if (seatingStudent == 0) {
                        emptyCnt++;
                        continue;
                    }
                    if (likes[student].contains(seatingStudent)) {
                        likeCnt++;
                    }
                }
                Seat seat = new Seat(i, j, likeCnt, emptyCnt);
                possibleSeats.add(seat);
            }
        }
        Collections.sort(possibleSeats);
        Seat bestSeat = possibleSeats.get(0);
        board[bestSeat.r][bestSeat.c] = student;
    }

    static int calSatisfied() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int student = board[i][j];
                int likeCnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dx[d];
                    int nc = j + dy[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }

                    int seatingStudent = board[nr][nc];
                    if (likes[student].contains(seatingStudent)) {
                        likeCnt++;
                    }
                }
                if (likeCnt != 0) {
                    sum += (int) Math.pow(10, likeCnt-1);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = 1;
        for (int tc = 1; tc <= testcase; tc++) {
            N = Integer.parseInt(in.readLine());
            board = new int[N][N];
            likes = new Set[N*N+1];
            for (int i = 0; i <= N*N; i++) {
                likes[i] = new HashSet<>();
            }
            existingStudents = new HashSet<>();

            for (int i = 0; i < N*N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                int student = Integer.parseInt(st.nextToken());
                for (int j = 0; j < 4; j++) {
//                    likes[student][j] = Integer.parseInt(st.nextToken());
                    likes[student].add(Integer.parseInt(st.nextToken()));
                }
                solution(student);
            }
            System.out.println(calSatisfied());
        }

    }
    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
