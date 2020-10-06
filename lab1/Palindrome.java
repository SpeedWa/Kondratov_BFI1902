public class Palindrome {
    public static void main(String[] args) {
        // Мы проверяем все аргументы, введенные пользователем, на наличие среди них строк-палиндромов
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.println("Является ли " + s + " палиндромом?");
            System.out.println("Ответ: " + isPalindrome(s) + "\n");
        }
    }

    // В методе reverseString мы создаем новую строку и записываем в нее перевернутое содержимое оригинальной строки
    // Метод возвращает полученную строку
    public static String reverseString(String s) {
        String reversed = "";
        for(int i= s.length() - 1; i >= 0; i--) {
            reversed = reversed + s.charAt(i);
        }
        return reversed;
    }

    // В методе isPalindrome мы создаем перевернутую копию строки и сравниваем её с оригинальной строкой
    // Если строки равны, то оригинальная строка - палиндром, метод возвращает true
    // Если строки не равны, то метод возвращает false
    public static boolean isPalindrome(String s) {

        String reversed = reverseString(s);
        if (s.equals(reversed)) {
            return true;
        }
        else {
            return false;
        }
    }

}