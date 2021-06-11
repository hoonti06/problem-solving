package programmers.p77884_약수의_개수와_덧셈;

public class Solution {

    static int[] cnt = new int[1001];
    static void cal() {
        for (int i = 1; i <= 1000; i++) {
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) cnt[i]++;
            }
        }
    }

    public int solution(int left, int right) {
        cal();

        int answer = 0;
        for (int i = left; i <= right; i++) {
            if (cnt[i] % 2 == 0) answer += i;
            else answer -= i;
//            System.out.println(cnt[i]);
        }
//        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        s.solution(13, 17);
        s.solution(24, 27);
    }
}
