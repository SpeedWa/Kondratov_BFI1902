import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class module5 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(encrypt("Hello")));
        int[] array1 = {72, 33, -73, 84, -12, -3, 13, -13, -68};
        System.out.println(decrypt(array1));

        System.out.println(canMove("Ладья", "A8", "A1"));

        System.out.println(canComplete("butl", "beautiful"));

        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));

        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"})));

        System.out.println(validateCard(1234567890123452L));

        System.out.println( numToEng(128));
        System.out.println(numToRus(128));

        System.out.println(getSha256Hash("password123"));

        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));

        System.out.println(hexLattice(19));


    }

    public static int[] encrypt(String str) {
        int charCode = 0;
        int[] arrEncrypt = new int[str.length()];
        for(int i = 0; i < str.length() ; i++) {
            arrEncrypt[i] = str.charAt(i) - charCode;
            charCode = str.charAt(i);
        }
        return arrEncrypt;
    }
    public static String decrypt(int[] arr) {
        String str = "";
        int charCode = 0;
        for (int j : arr) {
            str += (char) (j + charCode);
            charCode = j + charCode;
        }
        return str;
    }

    public static boolean canMove(String str, String tek, String tsel) {
        int h=Math.abs((tek.charAt(0) - 'A') - (tsel.charAt(0) - 'A')); // Горизонтальное передвижение
        int v=Math.abs(Integer.parseInt(tek.substring(1,2)) - Integer.parseInt(tsel.substring(1,2))); // Вертикальное передвижение
        switch (str) {
            case "Король":
                // Передвижение только по одной клетке
                return h <= 1 && v <= 1;
            case "Ферзь":
                // Движение или горизонтально или вертикально, или горизонтальное = вертикальному
                return (h == 0 || v == 0) || h == v;
            case "Ладья":
                // Передвижение только горизонтальное или вертикальное
                return h == 0 || v == 0;
            case "Слон":
                // Передвижение диагональное( горизонтальное = вертикальному)
                return h == v;
            case "Конь":
                // Вертикальное = 1 и горизонтальное = 2, то есть буквой Г, и наоборот
                return (h == 2 && v == 1) || (h == 1 && v == 2);
            case "Пешка":
                // Передвижение только вертикальное и только на 2 клетки максимум
                return h == 0 && v <= 2;
        }
        return false;
    }

    public static boolean canComplete(String str, String word) {
        int count = 0;
        for(int i = 0, j = 0; i < word.length(); i++){
            if(str.charAt(j) == word.charAt(i)) {
                count++;
                j++;
            }
        }
        return count == str.length();
    }

    public static int sumDigProd(int... n) {
        int sum = 0;
        for (int i : n)
            sum += i;

        while (sum > 9) {
            int p = 1;
            while (sum != 0) {
                p *= sum % 10;
                sum /= 10;
            }
            sum = p;
        }
        return sum;
    }

    public static String[] sameVowelGroup(String[] words) {
        int count = 0, k = 0;
        String[] res= {};
        String vow = words[0].replaceAll("[^aeiou]","");
        for (String word : words) {
            if (word.replaceAll("[^aeiou]", "").replaceAll("[" + vow + "]", "").length() == 0)
                count++;
        }
        res = new String[count];
        for (String word : words) {
            if (word.replaceAll("[^aeiou]", "").replaceAll("[" + vow + "]", "").length() == 0) {
                res[k] = word;
                k++;
            }
        }
        return res;
    }

    public static boolean validateCard(long num) {
        String string = String.valueOf(num);
        if (string.length() >= 14 && string.length() <= 19) {
            int lastDigit = Integer.parseInt(string.substring(string.length() - 1));
            string = string.substring(0, string.length() - 1);
            StringBuilder builder = new StringBuilder(string);
            string = builder.reverse().toString();
            int[] arr = new int[string.length()];
            long sum = 0;
            for (int i = 0; i < arr.length; i++) {
                int number = Integer.parseInt("" + (string.charAt(i)));
                int a = number;
                if ((i + 1) % 2 != 0) {
                    a = number * 2;
                    if (a >= 10) {
                        int rest = a / 10;
                        a = a % 10;
                        a = a + rest;
                    }
                }
                arr[i] = a;
                sum += arr[i];
            }
            return (10 - ((int) sum % 10)) == lastDigit;
        } else
            return false;
    }

    public static String numToEng(int n) {
        final String[] ones = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        final String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        final String[] tens1 = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String s = "";
        if (n == 0) {
            return ones[0];
        }
        if (n >= 100) {
            s += ones[n / 100] + " hundred";
            n = n % 100;
            if (n != 0) {
                s += " ";
            }
        }
        if (n >= 20) {
            s += tens[n / 10];
            n = n % 10;
            if (n != 0) {
                s += " ";
            }
        }
        else
        if (n >= 10) {
            s += tens1[n % 10];
            n = 0;
        }
        if (n > 0) {
            s += ones[n];
        }
        return s;
    }

    public static String numToRus(int n) {
        final String[] ones = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        final String[] tens = {"", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        final String[] tens1 = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
        final String[] hund = {"", "сто", "двести", "триста", "четыреста", "пятсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        String s = "";
        if (n == 0) {
            return ones[0];
        }
        if (n >= 100) {
            s += hund[n / 100];
            n = n % 100;
            if (n != 0) {
                s += " ";
            }
        }
        if (n >= 20) {
            s += tens[n / 10];
            n = n % 10;
            if (n != 0) {
                s += " ";
            }
        }
        else
        if (n >= 10) {
            s += tens1[n % 10];
            n = 0;
        }
        if (n > 0) {
            s += ones[n];
        }
        return s;
    }

    public static String getSha256Hash(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String correctTitle(String str) {
        str = " " + str.toLowerCase();
        for(int i=0;i < str.length();i++) {
            if(str.charAt(i) == ' ' || str.charAt(i) == '-') {
                str = str.substring(0, i+1) + str.substring (i + 1, i + 2).toUpperCase() + str.substring(i+2);
            }
        }
        str = str.replaceAll("And ","and ").replaceAll("The ", "the ").replaceAll("The", "the").
                replaceAll("Of ", "of ").replaceAll("In ","in ").trim();
        return str;
    }

    public static String hexLattice(int n) {
        int i = 0;
        boolean isHexLattice = false;
        while (3 * i * (i+1) + 1 <= n){
            if (3 * i * (i + 1) + 1 == n) {
                isHexLattice = true;
                break;
            }
            i++;
        }
        String str = "";
        if (isHexLattice){
            int l = i;
            int m = i;
            String str2;
            for (int j = 0; j < 2 * i - 1; j++){
                str += "\n";
                str2 = "";
                for (int k = 1; k < m; k++){
                    str2 +=  " ";
                }
                str += str2;
                for (int k = 0; k < l; k++){
                    str +=  " o";
                }
                str += str2 + " ";
                l += (j < i-1) ? 1 : -1;
                m += (j < i-1) ? -1 : 1;
            }
            return str;
        } else
            return "Invalid";
    }


}