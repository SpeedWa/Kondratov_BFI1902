import java.util.*;

public class module6 {

    public static void main(String[] args) {
        System.out.println("1) " + bell(3));

        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("flag"));
        System.out.println(translateSentence("I like to eat honey waffles."));

        System.out.println(validColor("rgb(255,256,255)"));

        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{}));

        System.out.println(Arrays.toString(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")));

        System.out.println(ulam(206));

        System.out.println(longestNonrepeatingSubstring("abcabcbb"));

        System.out.println(convertToRoman(3999));

        System.out.println(formula("16 * 10 = 160 = 14 + 120"));

        System.out.println(palindromeDescendant(11211230));
    }

    public static int bell(int n) {
        int[] row = new int[n];
        int previous;
        int temp;
        row[0] = 1;
        for (int i = 1; i < n; ++i) {
            previous = row[0];
            row[0] = row[i - 1];
            for (int j = 1; j <= i; ++j) {
                temp = row[j];
                row[j] = row[j - 1] + previous;
                previous = temp;
            }
        }
        return row[n - 1];
    }

    public static String translateWord(String word) { // Слово
        if (word.length() == 0) {
            return "";
        }

        char firstChar = word.charAt(0);
        if (isVowel(firstChar, false)) {
            return word + "yay";
        }

        int index = 1;
        for (; index < word.length(); ++index)
            if (isVowel(word.charAt(index), true)) {
                break;
            }

        if (Character.isUpperCase(firstChar)) {
            if (word.length() == index) {
                return word + "ay";
            }
            return Character.toUpperCase(word.charAt(index)) + word.substring(index + 1)
                    + Character.toLowerCase(firstChar) + word.substring(1, index)
                    + "ay";
        }

        return word.substring(index) + word.substring(0, index) + "ay";
    }

    public static String translateSentence(String sentence) {
        StringBuilder sentenceBuilder = new StringBuilder();

        int start = 0;
        int index = 0;

        for (; index < sentence.length(); ++index) {
            char c = sentence.charAt(index);
            if (!Character.isLetter(c)) {
                sentenceBuilder.append(translateWord(sentence.substring(start, index)));
                sentenceBuilder.append(c);
                start = index + 1;
            }
        }
        if (index - start > 0) {
            sentenceBuilder.append(translateWord(sentence.substring(start, index)));
        }
        return sentenceBuilder.toString();
    }

    private static boolean isVowel(char c, boolean isYVowel) {
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                return true;
            case 'y':
            case 'Y':
                return isYVowel;
            default:
                return false;
        }
    }

    public static boolean validColor(String color) {
        String s = color.substring(color.indexOf("(") + 1, color.indexOf(")"));
        String[] parts = s.split(",");

        // Пробел после rgb(a)
        if (color.contains(" ("))
            return false;

        // Правильно ли количество чисел цвета
        if (color.contains("rgba") && parts.length != 4)
            return false;
        else if (color.contains("rgb(") && parts.length != 3)
            return false;
        boolean percentage = false;
        int num = 0;
        double dec = 0;

        for (String value : parts) {
            if (value.equals(""))
                return false;
            if (value.contains(".")) {
                dec = Double.parseDouble(value);
            } else {
                num = Integer.parseInt(value);
            }
            // ensure rgb part doesn't contain decimal
            if (!color.contains("rgba") && value.contains("."))
                return false;


            if (num < 0 || num > 255)
                return false;


            if (color.contains("rgba") &&
                    (value.contains(".") && dec > 1.0 ||
                            (value.contains(".") && dec < 0)))
                return false;
        }
        return true;
    }

    public static String stripUrlParams(String url, String[] paramsToStrip) {

        if (!url.contains("?")) // Проверяем, содержит ли адрес вопросительный знак
            return url;

        String[] parts = url.split("\\?");     // инициализируем массив с именем "parts", который будет содержать два элемента в виде строк.
        // Первый элемент - это первая половина входящего URL-адреса, вплоть до вопросительного знака.
        // И второй элемент - это вторая половина входящего URL-адреса после вопросительного знака
        StringBuilder sb = new StringBuilder(parts[0]); // Создаём экземпляр SB, содержащий первую половину адреса
        String[] params = parts[1].split("&"); // Массив, содержащий каждый параметр, содержащийся во второй половине входящего URL-адреса, который разбивается амперсандом
        HashMap<String, String> map = new HashMap<>(); // Создаём хэш-карту, содержащую строки как ключи, так и значения

        for (String param : params) {
            String[] parm = param.split("=");
            map.put(parm[0], parm[1]);
        }

        LinkedHashSet<String> strip = new LinkedHashSet<>();

        if(paramsToStrip != null) {
            strip.addAll(Arrays.asList(paramsToStrip));
        }

        sb.append("?");
        int k = 1;

        for(String key : map.keySet()) {
            if(!strip.contains(key))
            {
                if(k > 1)
                    sb.append("&");
                sb.append(key).append("=").append(map.get(key));
                k++;
            }
        }

        return sb.toString();
    }

    public static String[] getHashTags(String str) {
        str = str.replaceAll(",", "");
        String[] strArr = str.split(" ");
        int maxNum = 0, pos = 0;
        String longestWord = "", hashes = "";

        if(strArr.length < 3){
            for(String word:strArr){
                hashes += "#" + word.toLowerCase() + ",";
            }
        }else{
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < strArr.length; j++){
                    if (strArr[j].length() != maxNum) {
                        if(strArr[j].length() >= maxNum){
                            maxNum = strArr[j].length();
                            longestWord = strArr[j].toLowerCase();
                            pos = j;
                        }
                    }
                }
                strArr[pos] = " ";
                hashes += "#" + longestWord + ",";
                maxNum = 0;

            }
        }
        String[] res = hashes.split(",");

        return res;
    }

    public static int ulam(int n) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        int i, j;
        for (i = 3, j = 2; j < n; i++) {
            int count = 0;
            for (int k = 0; k < arr.size() - 1; k++) {
                for (int l = k + 1; l < arr.size(); l++) {
                    if (arr.get(k) + arr.get(l) == i)
                        count++;
                    if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }
            if (count == 1) {
                arr.add(i);
                j++;
            }
        }
        return i - 1;
    }

    public static String longestNonrepeatingSubstring(String str) {
        String res = "", temp = "";
        for(int i = 0; i < str.length(); ++i){
            for(int j = i; j < str.length(); ++j){
                if(!temp.contains(String.valueOf(str.charAt(j))))
                    temp += str.charAt(j);
                else{
                    if(temp.length() > res.length())
                        res = temp;
                    temp = "";
                    j = str.length();
                }
            }
        }
        return res;
    }

    public static String convertToRoman(int num) {
        String fs = "";
        while(num != 0){
            if(num >= 1000){
                num -= 1000;
                fs += "M";
            }
            else if(num >= 900){
                num -= 900;
                fs += "CM";
            }
            else if(num >= 500){
                num -= 500;
                fs += "D";
            }
            else if(num >= 400){
                num -= 400;
                fs += "CD";
            }
            else if(num >= 100){
                num -= 100;
                fs += "C";
            }
            else if(num >= 90){
                num -= 90;
                fs += "XC";
            }
            else if(num >= 50){
                num -= 50;
                fs += "L";
            }
            else if(num >= 40){
                num -= 40;
                fs += "XL";
            }
            else if(num >= 10){
                num -= 10;
                fs += "X";
            }
            else if(num >= 9){
                num -= 9;
                fs += "IX";
            }
            else if(num >= 5){
                num -= 5;
                fs += "V";
            }
            else if(num >= 4){
                num -= 4;
                fs += "IV";
            }
            else if(num > 0){
                num -= 1;
                fs += "I";
            }
        }
        return fs;
    }

    static int a = 4;
    public static int calc(String str) {
        str = str.replaceAll("[()]", "");
        str = str.replaceAll(" ", "");
        if(str.equals("a"))
            return a;
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String[] tokens;
            tokens = str.split("\\*", 2);
            if (tokens.length > 1) {
                return calc(tokens[0]) * calc(tokens[1]);
            }
            tokens = str.split("/", 2);
            if (tokens.length > 1) {
                return calc(tokens[0]) / calc(tokens[1]);
            }
            tokens = str.split("\\+", 2);
            if (tokens.length > 1) {
                return calc(tokens[0]) + calc(tokens[1]);
            }
            tokens = str.split("-", 2);
            if (tokens.length > 1) {
                return calc(tokens[0]) - calc(tokens[1]);
            }
            return 0;
        }
    }
    public static boolean formula(String str) {

        String[] equations = str.split("=");
        for (int i = 0; i < equations.length-1; i++)
        {
            if(calc(equations[i]) != calc(equations[i+1])) {
                return false;
            }
        }
        return true;

    }

    public static boolean palindromeDescendant(int num) {
        boolean isSym = false;
        while(num > 9) {
            if(isSymmetrical(num)) {
                isSym = true;
                break;
            }
            num = getSumofDigits(num);
        }
        return isSym;
    }
    public static boolean isSymmetrical(int num) {
        int reversenum = 0, n = num;
        if(n < 0)
            n = n * -1;
        while(n != 0) {
            reversenum = reversenum * 10;
            reversenum = reversenum + n % 10;
            n = n / 10;
        }
        return(reversenum == num);
    }
    public static int getSumofDigits(int n) {
        String iString = Integer.toString(n);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < iString.length() - 1; i += 2) {
            int i1 = Integer.parseInt(Character.toString(iString.charAt(i)));
            int i2 = Integer.parseInt(Character.toString(iString.charAt(i+1)));
            int num = i1 + i2;
            sb.append(Integer.toString(num));
        }

        return Integer.parseInt(sb.toString());
    }
}
