package programmers.kakao2021.p1_신규_아이디_추천;

import java.util.*;

public class Solution {
    public String solution(String new_id) {
        String answer = new_id;
        answer = answer.toLowerCase();
        answer = answer.replaceAll("[^a-z0-9\\-_.]", "");
        answer = answer.replaceAll("\\.{2,}", ".");
        answer = answer.replaceAll("^\\.", "");
        answer = answer.replaceAll("\\.$", "");
        if (answer.isEmpty()) answer = "a";

        if (answer.length() >= 16)
            answer = answer.substring(0, 15);
        answer = answer.replaceAll("\\.$", "");

        // step7
        char lastChar = answer.charAt(answer.length() - 1);
        while (answer.length() <= 2) answer += lastChar;

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution("...!@BaT#*..y.abcdefghijklm");
        s.solution("z-+.^.");
        s.solution("=.=");
        s.solution("123_.def");
        s.solution("abcdefghijklmn.p");
    }
}

