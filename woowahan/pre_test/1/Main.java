import java.util.*;

public class Main 
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		
		if (N % 400 == 0)
			System.out.println("Leap Year");
		else if (N % 100 == 0)
			System.out.println("Not Leap Year");
		else if (N % 4 == 0)
			System.out.println("Leap Year");
		else
			System.out.println("Not Leap Year");
	}
}
