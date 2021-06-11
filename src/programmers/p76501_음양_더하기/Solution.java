package programmers.p76501_음양_더하기;

class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for (int i = 0; i < absolutes.length; i++) {
            if (signs[i]) answer += absolutes[i];
            else answer -= absolutes[i];
        }
        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        Solution s = new Solution();

        s.solution(new int[]{4,7,12}, new boolean[]{true,false,true});
    }
}