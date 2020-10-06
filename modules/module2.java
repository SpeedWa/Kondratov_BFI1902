import java.util.Arrays;

public class module2 {
    public static void main(String[] args) {
        System.out.println(repeat("mice", 4));
        int[] array1 = {1, 4, 5, 20, -1};
        System.out.println(differenceMaxMin(array1));
        int[] array2 = {1, 1, 1};
        System.out.println(isAvgWhole(array2));
        int[] array3 = {1, 3, 6};
        System.out.println(Arrays.toString(cumulativeSum(array3)));
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(Fibonacci(7));
        System.out.println(isValid("590 1"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-ophobia"));
        System.out.println(boxSeq(2));
    }

    public static String repeat (String string, int num) {
        String newString = "";
        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < num; j++) {
                newString += string.charAt(i);
            }
        }
        return newString;
    }

    public static int differenceMaxMin (int[] array) {
        int max = -999999999;
        int min = 999999999;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }
        return max - min;
    }

    public static boolean isAvgWhole (int[] array) {
        double num = 0;
        for (int i = 0; i < array.length; i++)
            num += array[i];
        num /= array.length;
        return num == Math.floor(num);
    }

    public static int[] cumulativeSum (int[] array) {
        int[] num = new int [array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <= i; j++) {
                num[i] += array[j];
            }
        }
        return num;
    }

    public static int getDecimalPlaces (String string) {
        int amount = 0;
        boolean hardStop = false;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '.') {
                hardStop = true;
            }
            else if (hardStop) {
                amount++;
            }
        }
        return amount;
    }

    public static int Fibonacci (int a) {
        int num1 = 1;
        int num2 = 1;
        int num3 = 0;
        if (a == 1)
            return 1;
        else
        for (int i = 2; i <= a; i++) {
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }
        return num3;
    }

    public static boolean isValid (String string) {

        try {
            double num = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }

        return string.length() <= 5;

    }

    public static boolean isStrangePair (String string1, String string2) {
        return string1.charAt(0) == string2.charAt(string2.length() - 1)
                && string1.charAt(string1.length() - 1) == string2.charAt(0);
    }

    public static boolean isPrefix(String string1, String string2) {
        for (int i = 0; i < string2.length() - 1; i++) {
            if (string1.charAt(i) != string2.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean isSuffix(String string1, String string2) {
        int hardIndex = 1;
        for (int i = string1.length() - string2.length() + 1; i < string1.length(); i++) {
            if (string1.charAt(i) != string2.charAt(hardIndex))
                return false;
            hardIndex++;
        }
        return true;
    }

    public static int boxSeq(int step) {
        int num = 0;
        for (int i = 1; i <= step; i++) {
            if (i % 2 == 1) {
                num += 3;
            }
            else
                num -= 1;
        }
        return num;
    }
}
