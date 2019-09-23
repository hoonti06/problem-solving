#include <string>
#include <iostream>
#include <vector>
#include <regex>

using namespace std;


int solution(vector<string> emails)
{
   int answer = 0;
   regex reg("^[a-z\\.]+@(?:\\w+\\.)+[com|net|org]+$");

   for (int i = 0; i < emails.size(); i++)
   {
      string cur = emails[i];
      if (regex_match(cur, reg))
         answer++;
   }
   return answer;
}

int main()
{
   freopen("in1.txt", "r", stdin);

   int N;
   cin >> N;
   vector<string> vec(N);

   for (int i = 0; i < N; i++)
      cin >> vec[i];

   cout << solution(vec) << endl;
}
