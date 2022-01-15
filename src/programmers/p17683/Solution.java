package programmers.p17683;

public class Solution {
    class Node {
        int playTime;
        String title;
        String melody;

        Node(String[] strings) {
            String[] startSplit = strings[0].split(":");
            int startTime = 60 * Integer.parseInt(startSplit[0]) + Integer.parseInt(startSplit[1]);
            String[] endSplit = strings[1].split(":");
            int endTime = 60 * Integer.parseInt(endSplit[0]) + Integer.parseInt(endSplit[1]);
            this.playTime = endTime - startTime;

            this.title = strings[2];

            strings[3] = replace(strings[3]);
            int len = strings[3].length();
            int quot = this.playTime / len;
            int rem = this.playTime % len;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < quot; i++) {
                sb.append(strings[3]);
            }
            sb.append(strings[3], 0, rem);

            this.melody = sb.toString();
        }
    }

    public String solution(String m, String[] musicinfos) {
        m = replace(m);
        String res = "(None)";

        int maxPlayTime = 0;
        for (String info : musicinfos) {
            Node node = new Node(info.split(","));
            if (node.melody.contains(m) && maxPlayTime < node.playTime) {
                maxPlayTime = node.playTime;
                res = node.title;
            }
        }
        return res;
    }

    public String replace(String str) {
        str = str.replace("C#", "c");
        str = str.replace("D#", "d");
        str = str.replace("F#", "f");
        str = str.replace("G#", "g");
        str = str.replace("A#", "a");
        return str;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(s.solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
        System.out.println(s.solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(s.solution("CCB", new String[]{"03:00,03:10,FOO,CCB#", "04:00,04:08,BAR,CCB"}));
    }
}
