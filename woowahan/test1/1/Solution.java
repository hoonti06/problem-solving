import java.util.*;

class Solution{
	public int solution(String s) {

		int openCnt = 0, closeCnt = 0;

		int i = -1, j = s.length();

		boolean isFin = false;

		boolean isLast = false;

		while (!isFin)
		{
			while (true)
			{
				i++;
				if (s.charAt(i) == '(')
				{
					break;
				}
				if (i >= j || i >= s.length()-1)
				{
					if (i >= s.length()-1)
						i++;
					isFin = true;   
					break;
				}
			}
			if (isFin)
			{
				if (isLast == true)
					openCnt = s.length()-1;
				break;
			}

			while (true)
			{
				j--;
				if (s.charAt(j) == ')')
					break;
				if (j <= i || j < 0)
				{
					isFin = true;   
					break;
				}
			}
			if (isFin)
			{
				if (isLast == true)
					closeCnt = 1;
				break;
			}
			openCnt = i; closeCnt = j;

			System.out.println(i + ", " + j);
		}

		System.out.println("hi " + s);


		return i;
	}
	public static void main(String[] args)
	{
		Solution sol = new Solution();
		int a = sol.solution("))");
		System.out.println("res : " + a);
	}
}
