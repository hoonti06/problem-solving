#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) 
{
    vector<int> answer;

	queue<int> progressQ, speedQ;

	while (progresses.size() > 0) 
	{
		for (int i = 0; i < progresses.size(); i++)
		{
			int curProgress = progresses[i];
			int curSpeed = speeds[i];

			if (progresses[i] < 100)
				progresses[i] += speeds[i];
		}

		int count = 0;
		for (int i = 0; i < progresses.size(); i++)
		{
			if (progresses[i] >= 100)
			{
				i--;
				count++;
				progresses.erase(progresses.begin());
				speeds.erase(speeds.begin());
			}
			else
				break;
		}
		if (count != 0)
			answer.push_back(count);
	}

    return answer;
}

int main() 
{
	vector<int> p, s;
	p.push_back(93);
	p.push_back(30);
	p.push_back(55);

	s.push_back(1);
	s.push_back(30);
	s.push_back(5);

	vector<int> v = solution(p, s);
	for (int i = 0; i < v.size(); i++)
		printf("%d\n", v[i]);
}
