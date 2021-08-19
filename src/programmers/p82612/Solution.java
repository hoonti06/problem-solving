package programmers.p82612;

public class Solution {
    public long solution(int price, int money, int count) {
        long total = (long)price * count * (count + 1)/2;
        long answer = total - money;
        if (answer < 0) {
            answer = 0;
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(3, 20, 4);
    }
}
