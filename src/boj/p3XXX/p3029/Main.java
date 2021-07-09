package boj.p3XXX.p3029;

import java.io.*;
import java.util.*;

public class Main {
    static class MyDate {
        int hour, min, sec;
        MyDate(int hour, int min, int sec) {
            this.hour = hour;
            this.min = min;
            this.sec = sec;
        }

        MyDate subtract(MyDate other) {
            int thisSec = this.hour * 3600 + this.min * 60 + this.sec;
            int otherSec = other.hour * 3600 + other.min * 60 + other.sec;

            if (thisSec <= otherSec) {
                thisSec += 3600 * 24;
            }

            int resSec = thisSec - otherSec;
            int resHour = resSec / 3600;
            resSec -= resHour * 3600;
            int resMin = resSec / 60;
            resSec -= resMin * 60;
            return new MyDate(resHour, resMin, resSec);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), ":");
        int startHour = Integer.parseInt(st.nextToken());
        int startMin = Integer.parseInt(st.nextToken());
        int startSec = Integer.parseInt(st.nextToken());
        MyDate startDate = new MyDate(startHour, startMin, startSec);

        st = new StringTokenizer(in.readLine(), ":");
        int endHour = Integer.parseInt(st.nextToken());
        int endMin = Integer.parseInt(st.nextToken());
        int endSec = Integer.parseInt(st.nextToken());
        MyDate endDate = new MyDate(endHour, endMin, endSec);

        MyDate res = endDate.subtract(startDate);
        System.out.printf("%02d:%02d:%02d\n", res.hour, res.min, res.sec);
    }
}
