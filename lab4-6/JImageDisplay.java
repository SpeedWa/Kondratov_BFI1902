import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;


/**
 * Данный класс отвечает за отрисовку изображения фрактала
 */
public class JImageDisplay extends JComponent{

    private final BufferedImage image;


    /**
     * Конструктор. Принимает ширину и высоту изображения.
     * Создает изображение заданных размеров и сохраняет его в image
     */
    JImageDisplay(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dim = new Dimension(width, height);
        super.setPreferredSize(dim);
    }

    /**
     * Данный метод рисует изображение фрактала
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    /**
     * Устанавливает пиксель изображения в соответствующий цвет
     */
    public void drawPixel(int x, int y, int rgbColor) {
        image.setRGB(x, y, rgbColor);
    }

    /**
     * Возвращает image
     */
    public BufferedImage getImage() {
        return image;
    }
}
