import java.util.*;

public class Solution
{
	int solution(int[] A, int[] B)
	{
		int n = A.length;
		int m = B.length;
		Arrays.sort(A);
		Arrays.sort(B);
		int i = 0;
		for (int k = 0; k < n; k++)
		{
			if (i < m-1 && B[i] < A[k])
				i+=1;
			if (A[k] == B[i])
				return A[k];
			else
				k--;
		}
		return -1;
	}

	public static void main(String[] args)
	{
		int[] a = { 1, 3, 2, 1 };
		int[] b = { 4, 3, 5, 3, 2 };

		Solution sol = new Solution();
		int res = sol.solution(b, a);
		System.out.println(res);
	}
}
