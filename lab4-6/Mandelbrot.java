import java.awt.geom.Rectangle2D;


/**
 * Данный класс отвечает за Mandelbrot фрактал
 */
public class Mandelbrot extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000;

    /**
     * Данный метод принимает экземпляр класса Rectangle2D.Double
     * и изменяет его свойства, как установлено в задании к лабораторной.
     */
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }

    /**
     * Данный метод берет координату пикселя и по нему вычислеяет количество итераций
     */
    public int numIterations(double x, double y) {

        int count = 0;
        double re = 0;
        double im = 0;

        while (re * re + im * im <= 4) {
            double nextRe = re * re - im * im + x;
            double nextIm = 2 * re * im + y;

            re = nextRe;
            im = nextIm;
            if (count++ == Mandelbrot.MAX_ITERATIONS) return -1;
        }

        return count;
    }

    /**
     * Для combo-box
     */
    public String toString() {
        return "Mandelbrot";
    }
}