package programmers.kakao2022.p92335;

import java.util.*;

public class Solution {
    boolean[] isPrimes = new boolean[16_000_001];

    public void calPrimes() {
        Arrays.fill(isPrimes, true);
        isPrimes[1] = false;

        for (int i = 2; i < 4_001; i++) {
            if (!isPrimes[i]) {
                continue;
            }
            for (int j = i + i; j < 16_000_001; j += i) {
                isPrimes[j] = false;
            }
        }
    }

    public int solution(int n, int k) {
        calPrimes();

        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int remain = n % k;
            sb.append(remain);
            n /= k;
        }

        int cnt = 0;
        String[] split = sb.reverse().toString().split("0");
        for (String str : split) {
            if (str.isEmpty()) {
                continue;
            }

            long num = Long.parseLong(str);
            if (num > 16_000_000) {
                if (isPrime(num)) {
                    cnt++;
                }
            } else {
                int intNum = (int) num;
                if (isPrimes[intNum]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public boolean isPrime(long num) {
        long sqrtNum = (long) Math.sqrt(num);
        for (int i = 2; i <= sqrtNum; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(437674, 3);
        s.solution(110011, 10);
    }
}
