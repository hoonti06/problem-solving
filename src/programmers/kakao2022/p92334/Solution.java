package programmers.kakao2022.p92334;

import java.util.*;

public class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reportingIdsMap = new LinkedHashMap<>();
        Map<String, Integer> reportedCount = new HashMap<>();
        for (String id : id_list) {
            reportingIdsMap.put(id, new HashSet<>());
            reportedCount.put(id, 0);
        }

        Set<String> stoppedIds = new HashSet<>();
        for (String str : report) {
            String[] split = str.split(" ");
            String reportedId = split[1];
            Set<String> reportingIdsPerUser = reportingIdsMap.get(split[0]);
            if (!reportingIdsPerUser.contains(reportedId)) {
                reportingIdsPerUser.add(reportedId);
                int nextCount = reportedCount.get(reportedId) + 1;
                reportedCount.put(reportedId, nextCount);
                if (nextCount >= k) {
                    stoppedIds.add(reportedId);
                }
            }
        }

        int n = id_list.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            Set<String> reportingIdsPerUser = reportingIdsMap.get(id_list[i]);
            for (String reportedId : reportingIdsPerUser) {
                if (stoppedIds.contains(reportedId)) {
                    answer[i]++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2);
        s.solution(new String[]{"con", "ryan"}, new String[]{"ryan con", "ryan con", "ryan con", "ryan con"}, 3);
    }

}
