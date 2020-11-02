import java.util.ArrayList;

public class module3 {
    public static void main(String[] args) {
        System.out.println(solutions(1, 0, 0));
        System.out.println(findZip("all zip files are compressed"));
        System.out.println(checkPerfect(6));
        System.out.println(flipEndChars("acbd"));
        System.out.println(isValidHexCode("#AC23D"));
        int[] arr1 = {1, 1};
        int[] arr2 = {1};
        System.out.println(same(arr1, arr2));
        System.out.println(isKaprekar(297));
        System.out.println(longestZero("1000100100"));
        System.out.println(nextPrime(14));
        System.out.println(rightTriange(70, 130, 110));
    }

    public static int solutions (int a, int b, int c) {
        int d = b * b - 4 * a * c;
        if (d == 0) {
            return 1;
        } else if (d < 0) {
            return 0;
        } else {
            return 2;
        }
    }

    public static int findZip(String string) {
        int firstIndex = string.indexOf("zip");
        int secondIndex = string.indexOf("zip", firstIndex + 1);
        return secondIndex;
    }

    public static boolean checkPerfect(int x) {
        int sum = 0;
        for (int i = 1; i < x; i++) {
            if (x % i == 0) {
                sum += i;
            }
        }

        return sum == x;
    }

    public static String flipEndChars(String string) {
        if (string.length() < 2) {
            return "Incompatible.";
        } else if (string.charAt(0) == string.charAt(string.length() - 1)) {
            return "Two's a pair.";
        } else {
            char[] line = string.toCharArray();
            char symbol = line[0];
            line[0] = line[line.length - 1];
            line[line.length-1] = symbol;
            return new String(line);
        }
    }
    public static boolean isValidHexCode (String string) {
        return string.matches("#[0-9a-fA-F]+");
    }

    public static boolean same (int[] arr1, int[] arr2) {
        // Поиск уникальных элементов по первому массиву
        int diffValues1;
        ArrayList<Integer> diffArr1 = new ArrayList<>();

        for (int i : arr1) {
            if (!diffArr1.contains(i)) {
                diffArr1.add(i);
            }
        }

        if(diffArr1.size() == 1){
            diffValues1 = 0;
        }
        else{
            diffValues1 = diffArr1.size();
        }
        // Поиск уникальных элементов по второму массиву
        int diffValues2;
        ArrayList<Integer> diffArr2 = new ArrayList<>();

        for (int i : arr2) {
            if (!diffArr2.contains(i)) {
                diffArr2.add(i);
            }
        }

        if(diffArr2.size() == 1){
            diffValues2 = 0;
        }
        else{
            diffValues2 = diffArr2.size();
        }

        return diffValues1 == diffValues2;
    }


    public static boolean isKaprekar(int number) {

        if (number == 1)
            return true;


        int temp = number * number;
        int numberLength= 0;

        while (temp != 0)
        {
            numberLength++;
            temp /= 10;
        }

        temp = number * number;

        for (int i = 1; i < numberLength; i++)
        {
            int numCut = (int) Math.pow(10, i);

            int sum = temp / numCut + temp % numCut;
            if (sum == number)
                return true;
        }

        return false;
    }

    public static String longestZero (String string) {
        int zeroLengthOverall = 0;
        int zeroLengthCurrent = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '0') {
                zeroLengthCurrent++;
                if (zeroLengthCurrent > zeroLengthOverall) {
                    zeroLengthOverall = zeroLengthCurrent;
                }

            } else {
                zeroLengthCurrent = 0;
            }
        }

        StringBuilder newString = new StringBuilder("");
        for (int i = 0; i< zeroLengthOverall; i++) {
            newString.insert(i, '0');
        }

        return new String(newString);
    }

    public static int nextPrime(int x) {

        boolean currentPrime = true;


        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                currentPrime = false;
                break;
            }
        }

        if (currentPrime) {
            return x;
        }

        while (!currentPrime) {

            x++;
            currentPrime = true;

            for (int i = 2; i < x; i++) {
                if (x % i == 0) {
                    currentPrime = false;
                    break;
                }
            }
        }

        return x;
    }

    public static boolean rightTriange(int x, int y, int z) {
        return x * x + y * y == z * z || x * x + z * z == y * y || y * y + z * z == x * x;
    }
}
