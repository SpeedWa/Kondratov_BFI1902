import java.awt.geom.Rectangle2D;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import java.io.IOException;


/**
 * Данный класс отвечает за GUI
 */
public class FractalExplorer {

    private final int displaySize;
    private JImageDisplay display;
    private FractalGenerator generator;
    private final Rectangle2D.Double range;
    private JComboBox<FractalGenerator> box;
    private JButton reset;
    private JButton save;
    private final JFrame frame;
    private int rowsRemain;

    /**
     * Конструктор. Инициализирует displaySize, range, generator
     */
    public FractalExplorer(int displaySize) {
        this.displaySize = displaySize;
        this.range = new Rectangle2D.Double(0, 0, 0, 0);
        generator = new Mandelbrot();
        generator.getInitialRange(this.range);
        frame = new JFrame("Фракталы");
    }

    /**
     * Данный метод отрисовывает инициализирует GUI элементы и отрисовывает их
     */
    public void createAndShowGUI() {
        this.reset = new JButton("Сбросить");
        this.reset.setActionCommand("reset");

        this.save = new JButton("Сохранить изображение");
        this.save.setActionCommand("save");

        JPanel panelBottom = new JPanel();
        panelBottom.add(this.reset);
        panelBottom.add(this.save);

        JLabel label = new JLabel("Фракталы");

        this.box = new JComboBox<FractalGenerator>();
        this.box.addItem(new Mandelbrot());
        this.box.addItem(new Tricorn());
        this.box.addItem(new BurningShip());

        JPanel panelTop = new JPanel();

        panelTop.add(label);
        panelTop.add(this.box);

        this.display = new JImageDisplay(this.displaySize, this.displaySize);

        ActionHandler handler = new ActionHandler();

        this.box.addActionListener(handler);
        this.reset.addActionListener(handler);
        this.save.addActionListener(handler);

        MouseListener listener = new MouseListener();
        this.display.addMouseListener(listener);

        this.frame.add(this.display, BorderLayout.CENTER);
        this.frame.add(panelBottom, BorderLayout.SOUTH);
        this.frame.add(panelTop, BorderLayout.NORTH);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.setResizable(false);
    }

    /**
     * Данный метод благодаря свойству generator вычислет количество итерация для каждого x и y.
     * display.DrawPixel вызывется для изменения значения цвета пикселя в изображении фрактала.
     * display.repaint отрисует получисшееся изображение
     */
    private void drawFractal() {
        this.enableUI(false);
        this.rowsRemain = this.displaySize;
        for (int y = 0; y < displaySize; y++) {
            FractalWorker worker = new FractalWorker(y);
            worker.execute();
        }
    }

    /**
     * События для кнопки сброса, кнопки сохраниения изображения и combo-box
     */
    public class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == box) {
                generator = (FractalGenerator) box.getSelectedItem();
                generator.getInitialRange(range);
                drawFractal();
            }
            else if (e.getActionCommand().equals("save")) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);

                int status = chooser.showSaveDialog(frame);

                if (status == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(display.getImage(), "png", chooser.getSelectedFile());
                    }
                    catch (IOException error) {
                        JOptionPane.showMessageDialog(frame, error.getMessage(),
                                "Произошла ошибка при сохранении изображения",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else if (e.getActionCommand().equals("reset")) {
                generator.getInitialRange(range);
                drawFractal();
            }
        }
    }

    /**
     * Событие для нажатия кнопки мыши
     */
    public class MouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, e.getX());
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, e.getY());

            generator.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    /**
     * Данный метод отвечает за блокировку кнопки сброса, кнопки сохранения изображения и combo-box
     */
    void enableUI(boolean val) {
        reset.setEnabled(val);
        save.setEnabled(val);
        box.setEnabled(val);
    }

    /**
     * Класс для отрисовки в поточном режиме
     */
    private class FractalWorker extends SwingWorker<Object, Object> {
        int y;
        int[] rgbColor;

        public FractalWorker(int y) {
            this.y = y;
        }

        /**
         * Вычисляет цвет для каждого x в столбце y и сохраняет в массив rgbColor
         */
        protected Object doInBackground() {
            rgbColor = new int[displaySize];

            for (int x = 0; x < displaySize; x++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);

                int numIters = generator.numIterations(xCoord, yCoord);

                if (numIters == -1) {
                    rgbColor[x] = 0;
                }
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    rgbColor[x] = Color.HSBtoRGB(hue, 1f, 1f);
                }
            }

            return null;
        }

        /**
         * Вызывает отрисовку для каждого x в строке y.
         * Цвета берутся из массивав rgbColor
         */
        protected void done() {
            for (int x = 0; x < displaySize; x++) {
                display.drawPixel(x, y, rgbColor[x]);
            }

            display.repaint(0, 0, y, displaySize, 1);

            rowsRemain--;

            if (rowsRemain == 0) enableUI(true);
        }
    }

    /**
     * Запуск программы
     */
    public static void main(String[] args) {
        FractalExplorer explorer = new FractalExplorer(500);
        explorer.createAndShowGUI();
        explorer.drawFractal();
    }
}