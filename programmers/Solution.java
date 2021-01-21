class Solution {
    public long solution(String expression) {
        long answer = 0;






        return answer;
    }
	public static void main(String[] args) {
		String str = "1@2#3@4&&5";

		String aa[] = str.split("#|@|&");

		System.out.println("split 출력");
		System.out.println("aa.length : " + aa.length);
		for(int i=0; i<aa.length; i++){
			System.out.println("aa["+ i + "] : "+ aa[i]);
		}

		StringTokenizer st = new StringTokenizer(str,"#|@|&");

		System.out.println("\nStringTokenizer 출력");

		while(st.hasMoreTokens()){
			System.out.print(st.nextToken() + " ");
		}
	}
}

