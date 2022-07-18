import java.util.HashMap;
import java.util.Map;

public class Task {

    static int auxiliaryMethod(int n) {
/*
    0 ----- 0
    1 ----- 1
    2 ----- 20
    3 ----- 300
    4 ----- 4000

    n ----- n * 10^(n-1)
 */
        return (int) (n * Math.pow(10, n - 1));
    }

    static int amountOfGivenDigitInNumber(int n, int m) {
        int count = 0;
        while (n > 0) {
            if (n % 10 == m) {
                count++;
            }
            n = n / 10;
        }
        return count;
    }

    public static int countZeroDigitsFrom0ToTen_N_degrees(int length) {
/*

    0 ----- 1
    1 ----- 10
    2 ----- 180
    3 ----- 2700
    4 ----- 36000

    n ----- n * 9 * 10^(n-1)

 */
        if (length == -1 || length == 0) {
            return 1;
        }
        if (length == 1) {
            return 10;
        }
        return (int) (length * 9 * Math.pow(10, length - 1)) + countZeroDigitsFrom0ToTen_N_degrees(length - 1);
    }

    public static int auxiliaryMethodForZero(int length) {
/*

    0 ----- 1
    1 ----- 11
    2 ----- 120
    3 ----- 1300
    4 ----- 14000

    n ----- n * 9 * 10^(n-1)

 */
        if (length == 0) {
            return 1;
        }
        String st = "1" + (int) (length * Math.pow(10, length - 1));
        return (int) (Integer.parseInt(st));
    }
    
    public static int countForTheZeroDigitCase(int number) {

        int count = amountOfGivenDigitInNumber(number, 0);
        int temp = 0;
        while (number > 0) {
            if (number % 10 > 0 && number != 1) {
                if (number > 9) {
                    count += (number % 10) * amountOfGivenDigitInNumber(number / 10, 0) * Math.pow(10, temp);

                    count += auxiliaryMethodForZero(temp);
                    count += (number % 10 - 1) * auxiliaryMethod(temp);
                } else {
                    count += (number % 10 - 1) * auxiliaryMethod(temp);
                }
            }

            number = number / 10;
            temp++;
        }
        return count + countZeroDigitsFrom0ToTen_N_degrees(temp - 2);
    }


    public static int countForOtherDigits(int number, int digit) {

        int t = amountOfGivenDigitInNumber(number, digit);
        int k = 0;
        while (number > 0) {
            if (number % 10 > 0) {
                t += (number % 10) * amountOfGivenDigitInNumber(number / 10, digit) * Math.pow(10, k);
                if (number % 10 > digit) {
                    t += Math.pow(10, k);
                }
                t += (number % 10) * auxiliaryMethod(k);
            }
            number = number / 10;
            k++;
        }
        return t;
    }

    public static Map<Integer, Integer> taskMethod(int n) {

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, countForTheZeroDigitCase(n));
        for (int i = 1; i < 10; i++) {
            map.put(i, countForOtherDigits(n, i));
        }
        return map;
    }

    public static void main(String[] args) {
      
        int count = 0;
        for (long i = 0; i <= 4521; i++) {

            long j = i;

            while (j > 0) {
                if (j % 10 == 0) {
                    count++;
                }
                j = j / 10;
            }
        }
        System.out.println(count + 1);
        

        System.out.println(countForTheZeroDigitCase(4521));
        
    }
}
