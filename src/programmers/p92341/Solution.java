package programmers.p92341;

import java.util.*;

public class Solution {
    static class Car {
        String num;
        String in;
        String out;
        int minTimeSum;
        int fee;

        Car(String num) {
            this.num = num;
        }

        public void sumParkingMinutes() {
            String[] outSplit = out.split(":");
            int outHour = Integer.parseInt(outSplit[0]);
            int outMin = Integer.parseInt(outSplit[1]) + outHour * 60;

            String[] inSplit = in.split(":");
            int inHour = Integer.parseInt(inSplit[0]);
            int inMin = Integer.parseInt(inSplit[1]) + inHour * 60;

            minTimeSum += outMin - inMin;
            this.out = null;
            this.in = null;
        }
    }

    public int[] solution(int[] fees, String[] records) {
        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int perTime = fees[2];
        int perFee = fees[3];

        Map<String, Car> cars = new HashMap<>();
        for (String record : records) {
            String[] split = record.split(" ");
            String time = split[0];
            String carNum = split[1];
            Car car = cars.getOrDefault(carNum, new Car(carNum));
            if ("IN".equals(split[2])) {
                car.in = time;
            } else {
                car.out = time;
                car.sumParkingMinutes();
            }
            cars.put(carNum, car);
        }

        List<Car> carList = new ArrayList<>();
        for (Car car : cars.values()) {
            if (car.in != null && car.out == null) {
                car.out = "23:59";
                car.sumParkingMinutes();
            }
            int fee = defaultFee;
            int parkingTime = car.minTimeSum;
            if (parkingTime > defaultTime) {
                int overTime = parkingTime - defaultTime;
                int overFee = (int) Math.ceil((double) overTime / perTime) * perFee;
                fee += overFee;
            }
            car.fee = fee;
            carList.add(car);
        }

        carList.sort(Comparator.comparing(o -> o.num));
        int carCnt = carList.size();
        int[] answer = new int[carCnt];
        for (int i = 0; i < carCnt; i++) {
            answer[i] = carList.get(i).fee;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        {
            int[] ret = s.solution(new int[]{180, 5000, 10, 600},
                    new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
                            "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
            System.out.println(Arrays.toString(ret));
        }
        {
            int[] ret = s.solution(new int[]{120, 0, 60, 591},
                    new String[]{"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"});
            System.out.println(Arrays.toString(ret));
        }
        {
            int[] ret = s.solution(new int[]{1, 461, 1, 10},
                    new String[]{"00:00 1234 IN"});
            System.out.println(Arrays.toString(ret));
        }
    }
}
