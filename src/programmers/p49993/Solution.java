package programmers.p49993;

import java.util.*;

public class Solution {
    public int solution(String skill, String[] skill_trees) {
        Queue<Character> backup = new ArrayDeque<>();
        int N = skill.length();
        char[] skillChars = skill.toCharArray();
        Set<Character> setBackup = new HashSet<>();
        for (char ch : skillChars) {
            backup.offer(ch);
            setBackup.add(ch);
        }

        int res = 0;
        Queue<Character> q = new ArrayDeque<>();
        Set<Character> set = new HashSet<>();
        for (String skillTree : skill_trees) {
            q.clear();
            q.addAll(backup);
            set.addAll(setBackup);
            char[] skillTreeChars = skillTree.toCharArray();
            int i = 0;
            int M = skillTreeChars.length;
            boolean possible = true;
            while (!q.isEmpty() && i < M) {
                char cur = q.peek();
                if (cur == skillTreeChars[i]) {
                    q.poll();
                    set.remove(cur);
                } else if (set.contains(skillTreeChars[i])) {
                    possible = false;
                    break;
                }
                i++;
            }
            if (possible) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"});
    }
}
