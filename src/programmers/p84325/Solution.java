package programmers.p84325;

import java.util.*;

public class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        Map<String, Integer> langPreferMap = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            langPreferMap.put(languages[i], preference[i]);
        }

        String[] jobs = new String[table.length];
        int max = -1;
        String maxJob = "";
        for (int i = 0; i < table.length; i++) {
            StringTokenizer st = new StringTokenizer(table[i], " ");
            jobs[i] = st.nextToken();
            int sum = 0;
            for (int j = 5; j > 0; j--) {
                String curLang = st.nextToken();
                sum += j * langPreferMap.getOrDefault(curLang, 0);
            }

            // max 계산
            for (int j = 0; j < jobs.length; j++) {
                if (max < sum) {
                    max = sum;
                    maxJob = jobs[i];
                } else if (max == sum) {
                    if (maxJob.compareTo(jobs[i]) > 0) {
                        maxJob = jobs[i];
                    }
                }
            }
        }

        return maxJob;
    }

    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        {
            String[] tables = {"SI JAVA JAVASCRIPT SQL PYTHON C#",
                    "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
                    "HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
                    "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
                    "GAME C++ C# JAVASCRIPT C JAVA"
            };
            String[] languages = {"PYTHON", "C++", "SQL"};
            int[] preference = {7, 5, 5};
            s.solution(tables, languages, preference);
        }

        {
            String[] tables = {"SI JAVA JAVASCRIPT SQL PYTHON C#",
                    "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
                    "HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
                    "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
                    "GAME C++ C# JAVASCRIPT C JAVA"
            };
            String[] languages = {"JAVA", "JAVASCRIPT"};
            int[] preference = {7, 5};
            s.solution(tables, languages, preference);
        }
    }
}
