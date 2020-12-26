import java.util.ArrayList;
import java.util.HashSet;

public class module4 {
    public static void main(String[] args) {
        textProcesser(10 , 7, "hello my name is Bessie and this is my essay");
        System.out.println(split("((())())(()(()()))"));
        System.out.println(toCamelCase("hello_eda_bit"));
        System.out.println(toSnakeCase("helloEdaBit"));
        System.out.println(overTime(16, 18, 30, 1.8));
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(bugger(999));
        System.out.println(toStarShorthand("abbccc"));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham.") );
        System.out.println(trouble(1222345, 12345) );
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A') );

    }
    public static void textProcesser(int n,int k, String str){
        int i = 0;
        int spaceIndex1 = 0;
        int spaceIndex2 = 0;
        String buf = "";
        boolean f = true;
        boolean f1 = true;
        while (f){

            while(f1){
                spaceIndex1 = str.indexOf(" ");
                spaceIndex2 = str.indexOf(" ",spaceIndex1 + 1);
                if(spaceIndex1 < k && spaceIndex2 <= k){
                    char sim3=str.charAt(spaceIndex2);
                    if(sim3 ==' '){
                        i = spaceIndex2;
                    }
                    else {
                        i = str.indexOf(" ");
                    }
                    break;
                }else{
                    if(spaceIndex1 <= k && spaceIndex2 > k){
                        char sim3= str.charAt(spaceIndex2);
                        if(sim3 ==' '&& (spaceIndex2 == k + 1)){
                            i = spaceIndex2;
                        }
                        else{
                            i= str.indexOf(" ");
                        }
                        break;

                    }
                    break;
                }
            }
            System.out.println(str.substring(0,i));
            str = str.substring(i+1);
            if(str.length() < k){
                System.out.println(str);
                break;
            }

        }
    }

    public static String split(String string) {

        int brackets_left = 0;
        int brackets_right = 0;

        StringBuilder replace_string = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                brackets_left++;
                replace_string.append("(");
            } else if (string.charAt(i) == ')') {
                brackets_right++;
                replace_string.append(")");
            }

            if (brackets_left == brackets_right && brackets_left != 0) {

                brackets_left = 0;
                brackets_right = 0;
                replace_string.append(" ");
            }
        }

        return replace_string.toString();
    }

    public static String toCamelCase(String string) {

        StringBuilder replace_string = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '_' && i < string.length() - 1) {
                i++;
                char letter = string.charAt(i);
                replace_string.append(Character.toUpperCase(letter));
            } else {
                replace_string.append(string.charAt(i));
            }
        }

        return replace_string.toString();
    }

    public static String toSnakeCase(String string) {

        StringBuilder replace_string = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z') {
                char letter = string.charAt(i);
                replace_string.append('_');
                replace_string.append(Character.toLowerCase(letter));
            } else {
                replace_string.append(string.charAt(i));
            }
        }

        return replace_string.toString();
    }

    public static double overTime(double start, double finish, double pay, double mult) {
        double resulting_payment = 0;
        for(double i = start; i < 17; i++) {
            resulting_payment += pay;
        }
        if(finish > 17) {
            for(double i = 17; i < finish; i++) {
                resulting_payment += (pay * mult);
            }
        }
        return resulting_payment;
    }

    public static String BMI(String string1, String string2) {

        StringBuilder height = new StringBuilder();
        StringBuilder weight = new StringBuilder();

        for (int i = 0; i < string1.length(); i++) {
            if (string1.charAt(i) == ' ') {
                break;
            }
            weight.append(string1.charAt(i));
        }

        double weight_num = Double.parseDouble(weight.toString());

        if (string1.contains("pounds")) {
            weight_num *= 0.453592;
        }

        for (int i = 0; i < string2.length(); i++) {
            if (string2.charAt(i) == ' ') {
                break;
            }
            height.append(string2.charAt(i));
        }

        double height_num = Double.parseDouble(height.toString());

        if (string2.contains("inches")) {
            height_num *= 0.0254;
        }

        double result = weight_num / Math.pow(height_num, 2);

        if (result < 18.5) {
            return Math.round(result * 10.0)/10.0 + " Underweight";
        } else if (result >= 25) {
            return Math.round(result * 10.0)/10.0 + " Overweight";
        } else {
            return Math.round(result * 10.0)/10.0 + "  Normal weight";
        }
    }
    public static int bugger(int a) {
        int num = 0;
        while (a >= 10) {
            int changed_a = a;
            a = 1;
            while (changed_a > 0) {
                a *= changed_a % 10;
                changed_a /= 10;
            }
            num++;
        }
        return num;
    }

    public static String toStarShorthand(String str) {
        String resulting_string = "";
        for(int i = 0; i < str.length(); i++){
            int c = 1;
            for(int j = i + 1; j < str.length(); j++){
                if(str.charAt(i) == str.charAt(j)) {
                    i++;
                    c++;
                }

            }
            if(c > 1) {
                resulting_string += str.charAt(i) + "*" + Integer.toString(c);
            }
            else {
                resulting_string += str.charAt(i);
            }
        }
        return resulting_string;
    }

    public static boolean doesRhyme(String s1, String s2) {
        String first= "";
        String second = "";
        String s3 = s1.toLowerCase();
        String s4 = s2.toLowerCase();
        s1 = s3.replaceAll("[.,!;:]", "");
        s2 = s4.replaceAll("[.,!;:]", "");
        for(int i = s1.length()-1; i > 0; i--) {
            if(s1.charAt(i) != ' ') {
                if(s1.charAt(i) == 'a' || s1.charAt(i) == 'o' || s1.charAt(i) == 'i' || s1.charAt(i) == 'e' || s1.charAt(i) == 'u')
                    first += s1.charAt(i);
            }
            else
                break;
        }
        for(int i = s2.length()-1; i > 0; i--) {
            if(s2.charAt(i) != ' ') {
                if(s2.charAt(i) == 'a' || s2.charAt(i) == 'o' || s2.charAt(i) == 'i' || s2.charAt(i) == 'e' || s2.charAt(i) == 'u')
                    second += s2.charAt(i);
            }
            else
                break;
        }
        if(first.equals(second))
            return true;
        else
            return false;
    }

    public static boolean trouble(int a, int b) {

        String first_num = String.valueOf(a);
        String second_num = String.valueOf(b);

        for (int i = 0; i < 10; i++) {
            int first_num_idx = first_num.indexOf(String.valueOf(i) + String.valueOf(i) + String.valueOf(i));
            int second_num_idx = second_num.indexOf(String.valueOf(i) + String.valueOf(i));
            if (first_num_idx != -1 && second_num_idx != -1) {
                return true;
            }
        }
        return false;
    }

    public static int countUniqueBooks(String str, char bookend) {
        HashSet s = new HashSet();
        boolean betweenBookEnds=false;
        for (char act:str.toCharArray()) {
            if (act==bookend)
                betweenBookEnds = !betweenBookEnds;
            else
            if (betweenBookEnds)
                s.add(act);
        }
        return s.size();
    }

}
