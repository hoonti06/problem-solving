
import java.util.Arrays;


public class Solution{

	public boolean solution(int[] A)
	{
//		System.out.println(A.length);
		int[] B;
		B = A.clone();
		Arrays.sort(B);
//		System.out.println(A);
//
		int[] change = new int[2];
		int cnt = 0;
		for (int i = 0; i < A.length; i++)
		{
			if (A[i] != B[i])
			{
				if (cnt >= 2)
				{
					cnt++;
					break;
				}
				change[cnt++] = i;
			}

			System.out.println(A[i] + ", " + B[i]);
		}

		if (cnt != 2)
			return false;

		if (A[change[0]] == B[change[1]] && A[change[1]] == B[change[0]])
			return true;
		else
			return false;

	}



	public static void main(String[] args)
	{
		int[] aa = { 1, 3, 5 };

		Solution sol = new Solution();
		System.out.println(sol.solution(aa));

	}
}
