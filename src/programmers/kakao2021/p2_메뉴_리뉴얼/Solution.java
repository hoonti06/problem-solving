package programmers.kakao2021.p2_메뉴_리뉴얼;

import java.util.*;

class Solution {

    static HashMap<String, Integer> map = new HashMap<>();

//    static int bitmasking(String str) {
//        int ret = 0;
//        for (int i = 0; i < str.length(); i++) {
//            char alphabet = str.charAt(i);
//            ret |= 1 << (alphabet - 'A');
//        }
//        return ret;
//    }
    static Set<Integer> possibleLen = new HashSet<>();

    static void split(char[] str) {
        int len = (int) Math.pow(2, str.length);
        int ret = 0;
        StringBuilder sb = new StringBuilder();
        for (int bitmask = 3; bitmask < len; bitmask++) {
            sb.setLength(0);
            for (int digit = 0; digit < str.length; digit++) {
                if ((bitmask & 1 << digit) > 0)
                    sb.append(str[digit]);
            }
            if (!possibleLen.contains(sb.length())) continue;
            String splitted = sb.toString();
            map.put(splitted, map.getOrDefault(splitted, 0) + 1);
        }
    }

    public String[] solution(String[] orders, int[] course) {
        for (int i = 0; i < course.length; i++)
            possibleLen.add(course[i]);

        int len = orders.length;
        for (int i = 0; i < len; i++) {
            String order = orders[i];
            char[] tmp = orders[i].toCharArray();
            Arrays.sort(tmp);
            split(tmp);
        }



        List<String> res = new ArrayList<>();
        String[] answer = {};
        int[] maxCnt = new int[26];
        List<String>[] aa = new ArrayList[26];
        for (int i = 0; i < 26; i++) aa[i] = new ArrayList<>();

        for (String str : map.keySet())
            maxCnt[str.length()] = Math.max(maxCnt[str.length()], map.get(str));

        for (String str : map.keySet()) {
            if (maxCnt[str.length()] > 1 && maxCnt[str.length()] == map.get(str))
                res.add(str);
        }


        Collections.sort(res);
        answer = new String[res.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = res.get(i);
        }
        return answer;
    }


//    public String[] solution(String[] orders, int[] course) {
//        HashSet<Integer> possible = new HashSet<>();
//        for (int i = 0; i < course.length; i++)
//            possible.add(course[i]);
//
//        System.out.println(possible.contains(2));
//
//        List<String> res = new ArrayList<>();
//        String[] answer = {};
//        int[] maxCnt = new int[26];
//        List<String>[] aa = new ArrayList[26];
//        for (int i = 0; i < 26; i++) aa[i] = new ArrayList<>();
//
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < orders.length-1; i++) {
//            int cur = bitmasking(orders[i]);
//            for (int j = i+1; j < orders.length; j++) {
//                int next = bitmasking(orders[j]);
//                int bitmask = cur & next;
//                if (map.containsKey(bitmask))
//                    map.put(bitmask, map.get(bitmask) + 1);
//                else
//                    map.put(bitmask, 1);
//            }
////            System.out.println(Integer.toBinaryString(cur));
//        }
//        HashMap<String, Integer> bb = new HashMap<>();
//        for (int bitmask : map.keySet()) {
//            StringBuilder sb = new StringBuilder();
//            for (int digit = 0; digit < 26; digit++) {
//                int v{Integer@512} 12 -> {Integer@508} 2al = bitmask & (1 << digit);
//                if (val > 0) sb.append((char)(digit + 'A'));
//            }
//            int cnt = map.get(bitmask);
//            if (possible.contains(sb.length())) {
//                bb.put(sb.toString(), cnt);
//                maxCnt[sb.length()] = Math.max(maxCnt[sb.length()], cnt);
//            }
//        }
//
//        for (String str : bb.keySet()) {
//            int cnt = bb.get(str);
//            if (cnt == maxCnt[str.length()])
//                res.add(str);
//        }
//
//        Collections.sort(res);
//        answer = new String[res.size()];
//        for (int i = 0; i < answer.length; i++) {
//            answer[i] = res.get(i);
//        }
//        return answer;
//    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
//        int[] course = {2, 3, 4};

//        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
//        int[] course = {2, 3, 5};

        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};


        String[] res = s.solution(orders, course);
        System.out.println(Arrays.toString(res));
    }

}
