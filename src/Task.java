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

    public static int fromZeroToTen_N_Degrees(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        return (int) ((9 * (n - 1) + 1) * Math.pow(10, n - 2) + n);
    }


    public static int auxiliaryMethodForZero(int length) {
/*
    0 ----- 0
    1 ----- 1*1*9^1
    2 ----- 1*1*9^2 + 2*2*9^1
    3 ----- 1*1*9^3 + 2*3*9^2 + 3*3*9^1
    4 ----- 1*1*9^4 + 2*4*9^3 + 3*6*9^2 + 4*4*9^1

    n ----- 1*combination(length -2 , 0)*9^k  + 2*combination(length -2 , 1)*9^(k-1) + ...

                ... +  n* combination(length -2 , k + 1 ) *9^1
 */
        if (length > 2) {
            int count = 0;
            int k = 0;
            int temp = length - 2;
            while (temp > 0) {

                count += combination(length - 2, k) * (k + 1) * Math.pow(9, temp);
                k++;
                temp--;
            }
            return count;
        }
        return 0;
    }

    public static int combination(int n, int k) {
        if (k == 0) return 1;
        if (n == 0) return 0;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }


    public static int countForTheZeroDigitCase(int number) {

        int count = 0;
        int temp = 0;
        while (number > 0) {
            if (number % 10 > 0) {
                if (number < 10) {
                    count += (number % 10 - 1) * auxiliaryMethod(temp);
                } else {
                    count += (number % 10) * amountOfGivenDigitInNumber(number / 10, 0);
                    count += auxiliaryMethodForZero(temp + 2);
                    count += (number % 10) * auxiliaryMethod(temp);
                }
            }
            number = number / 10;
            temp++;
        }
        return count + fromZeroToTen_N_Degrees(temp - 1);
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
        System.out.println(taskMethod(100));
    }
}
