public class Primes {
    public static void main(String[] args) {
        // Мы проверяем числа от 2 до 100 на наличие среди них простых чисел
        for (int i = 2; i < 100; i++) {
            System.out.println("Число " + i + " простое?\n" + "Ответ: " + isPrime(i) + "\n");
        }
    }

    // В методе isPrime выбранное нами число проходит проверку делением всеми числами от 0 до себя самого
    // Если хотя бы один из делителей производит нулевой остаток, то число - простое, метод возвращает true
    // Если этого не происходит, то метод возвращает false
    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0 )
                return false;
        }
        return true;
    }
}