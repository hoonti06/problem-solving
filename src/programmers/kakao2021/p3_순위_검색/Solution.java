package programmers.kakao2021.p3_순위_검색;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    static class Person /* implements Comparable<Person> */{
        String language;
        String type;
        String grade;
        String food;
        int number;

//        @Override
//        public int compareTo(Person o) {
//            if (this.number != o.number)
//                return Integer.compare(this.number, o.number);
//            if (!this.food.equals(o.food))
//                return this.food.compareTo(o.food);
//            if (!this.grade.equals(o.grade))
//                return this.grade.compareTo(o.grade);
//            if (!this.type.equals(o.type))
//                return this.type.compareTo(o.type);
//            return this.language.compareTo(o.language);
//        }
    }

    int[] solution(String[] info, String[] query) {

        List<Person> people = new ArrayList<>();
        for (String element : info) {
            String[] splited = element.split(" ");
            Person person = new Person();
            person.language = splited[0];
            person.type = splited[1];
            person.grade = splited[2];
            person.food = splited[3];
            person.number = Integer.parseInt(splited[4]);
            people.add(person);
        }

        for (String element : query) {
            String[] splited = element.split("and");
            String queryLan = splited[0].trim();
            String queryType = splited[1].trim();
            String queryGrade = splited[2].trim();
            String[] ssplited = splited[3].trim().split(" ");
            String queryFood = ssplited[0];
            int queryNumber = Integer.parseInt(ssplited[1]);

            Collections.sort(people, (o1, o2) -> {
                if (!o1.language.equals(o2.type) && !queryLan.equals("-"))
                    return o1.language.compareTo(o2.language);
                if (!o1.type.equals(o2.type) && !queryType.equals("-"))
                    return o1.type.compareTo(o2.type);
                if (!o1.grade.equals(o2.grade) && !queryGrade.equals("-"))
                    return o1.grade.compareTo(o2.grade);
                if (!o1.food.equals(o2.food) && !queryFood.equals("-"))
                    return o1.food.compareTo(o2.food);

                return Integer.compare(o1.number, o2.number);
//                if (o1.number != o2.number)
//                    return Integer.compare(o1.number, o2.number);
//                if (!o1.food.equals(o2.food) && !queryFood.equals("-"))
//                    return o1.food.compareTo(o2.food);
//                if (!o1.grade.equals(o2.grade) && !queryGrade.equals("-"))
//                    return o1.grade.compareTo(o2.grade);
//                if (!o1.type.equals(o2.type) && !queryType.equals("-"))
//                    return o1.type.compareTo(o2.type);
//                if (!o1.language.equals(o2.type) && !queryLan.equals("-"))
//                    return o1.language.compareTo(o2.language);
            });


        }

        return new int[20];
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] info = {"java backend junior pizza 150",
                        "python frontend senior chicken 210",
                        "python frontend senior chicken 150",
                        "cpp backend senior pizza 260",
                        "java backend junior chicken 80",
                        "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100",
                        "python and frontend and senior and chicken 200",
                        "cpp and - and senior and pizza 250",
                        "- and backend and senior and - 150",
                        "- and - and - and chicken 100",
                        "- and - and - and - 150"};
        s.solution(info, query);

    }
}
