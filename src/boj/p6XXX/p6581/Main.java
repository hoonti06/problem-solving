package boj.p6XXX.p6581;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int curCharCount = 0;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }

            StringTokenizer st = new StringTokenizer(line, " ");
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                int wordLen = word.length();
                if (word.equals("<br>")) {
                    curCharCount = 0;
                    sb.append("\n");
                } else if (word.equals("<hr>")) {
                    if (curCharCount > 0) {
                        curCharCount = 0;
                        sb.append("\n");
                    }
                    for (int i = 0; i < 80; i++) {
                        sb.append("-");
                    }
                    sb.append("\n");
                } else {
                    if (curCharCount == 0) {
                        sb.append(word);
                        curCharCount = wordLen;
                    }
                    else if (curCharCount + 1 + wordLen <= 80) {
                        sb.append(" ").append(word);
                        curCharCount += 1 + wordLen;
                    } else {
                        curCharCount = wordLen;
                        sb.append("\n").append(word);
                    }
                }
            }
        }

        if (sb.toString().charAt(sb.length()-1) != '\n') {
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
