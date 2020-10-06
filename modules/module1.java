public class module1 {
    public static void main(String[] args) {
        System.out.println(remainder(5, 3));
        System.out.println(triArea(2, 5));
        System.out.println(animals(2, 3, 5));
        System.out.println(profitableGamble(0.2, 50, 9));
        System.out.println(operation(24, 15, 9));
        System.out.println(ctoa('a'));
        System.out.println(addUpTo(3));
        System.out.println(nextEdge(4, 3));
        int[] array = {1, 4, 5};
        System.out.println(sumOfCubes(array));
        System.out.println(abcmath(5, 3, 4));


    }

    public static int remainder (int x, int y) {
        return x % y;
    }

    public static double triArea (double a, double h) {
        return 0.5 * a * h;
    }

    public static int animals (int chickens, int cows, int pigs) {
        return chickens * 2 + cows * 4 + pigs * 4;
    }

    public static boolean profitableGamble (double prob, int prize, int pay) {
        return prob * prize > pay;
    }

    public static String operation (int n, int a, int b) {
        if (a + b == n)
            return "added";
        else if (a - b == n)
            return "subtracted";
        else if (a * b == n)
            return "multiplied";
        else if (a / b == n)
            return "divided";
        else
            return "none";

    }

    public static int ctoa (char symbol) {
        return symbol;
    }

    public static int addUpTo (int x) {
        int num = 0;
        for (int i = 1; i <= x ; i++)
            num += i;
        return num;
    }

    public static int nextEdge (int a, int b) {
        return a + b - 1;
    }

    public static int sumOfCubes (int[] array) {
        int num = 0;
        for (int i = 0; i < array.length; i++)
            num += array[i]^3;
        return num;
    }

    public static boolean abcmath (int a, int b, int c) {
        int num = a;
        for (int i = 1; i <= b; i++ ) {
            num += a;
        }
        return num % c == 0;
    }

}
