import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Данный класс является основным и отвечает за запуск программы.
 * Происходит переход по ссылкам и сохранение их в waitingUrls
 * Посещенные ссылки сохраняются в processedUrls
 * В конце происходит вывод processedUrls
 */
public class Crawler {

    public static final String LINK_PREFIX = "<a href=\"";

    public LinkedList<URLDepthPair> waitingUrls;
    public LinkedList<URLDepthPair> processedUrls;

    public int maxDepth;

    public Crawler(URLDepthPair pair, int maxDepth) {
        this.waitingUrls = new LinkedList<>();
        this.processedUrls = new LinkedList<>();
        this.waitingUrls.add(pair);
        this.processedUrls.add(pair);
        this.maxDepth = maxDepth;
    }

    /**
     * Пока существуют ссылки, которые еще можно посетить вызываем метод workWithUrl
     */
    public void run() {
        while (waitingUrls.size() > 0) {
            try {
                URLDepthPair pair = waitingUrls.removeFirst();
                workWithUrl(pair);
            }
            catch (IOException e) {
                System.out.println("Произошла IOException ошибка");
            }
        }
    }

    /**
     * Данный метод находит информацию о ссылках на странице, переданной в url UrlDepthPair
     * Сначала посылаем Http запрос на страницу
     * Затем получаем ответ
     * Обрабатываем ответ построчно
     * Ищем все ссылки в тексте
     * Добавляем найденные ссылки в waitingUrls
     * Текущую посещенную ссылку добавляем к processedUrls
     */
    public void workWithUrl(URLDepthPair pair) throws IOException {
        int webPort = 80;
        String webServer = pair.getWebHost();

        Socket socket = new Socket(webServer, webPort);
        socket.setSoTimeout(3000);

        OutputStream os = socket.getOutputStream();

        PrintWriter writer = new PrintWriter(os, true);
        writer.println("GET " + pair.getDocPath() + " HTTP/1.1");
        writer.println("Host: " + pair.getWebHost());
        writer.println("Connection: close");
        writer.println();

        InputStreamReader isr = new InputStreamReader(socket.getInputStream());

        BufferedReader br = new BufferedReader(isr);

        while (true) {

            String line = br.readLine();

            if (line == null) {
                break;
            }

            int idx = 0;

            while (true) {
                idx = line.indexOf(LINK_PREFIX, idx);

                if (idx == -1) {
                    break;
                }

                int start = idx + LINK_PREFIX.length();
                int end = line.indexOf('"', start);

                if (end == -1) {
                    break;
                }

                String newURL = line.substring(start, end);
                int newDepth = pair.depth + 1;

                URLDepthPair newPair = new URLDepthPair(newURL, newDepth);

                if (newPair.isValid()) {
                    processedUrls.addLast(newPair);

                    if (newPair.depth < maxDepth) {
                        waitingUrls.addLast(newPair);
                    }
                }

                idx = end + 1;
            }
        }

        socket.close();
    }

    /**
     * Вывод всех посещенных ссылок
     */
    public void getSites() {
        while (!processedUrls.isEmpty()) {
            System.out.println(processedUrls.removeFirst().toString());
        }
    }

    /**
     * Запуск программы
     */
    public static void main(String[] args) {

        int maxDepth = 0;

        if (args.length != 2) {
            System.out.println("Используйте: java Crawler <URL> <depth>");
            System.exit(1);
        }

        String url = args[0];

        try {
            maxDepth = Integer.parseInt(args[1]);

            if (maxDepth < 0) {
                System.out.println("Глубина поиска должна быть больше 0");
                System.exit(1);
            }
        }
        catch (NumberFormatException nfe) {
            System.out.println("Используйте: java Crawler <URL> <depth>");
            System.exit(1);
        }

        URLDepthPair firstPair = new URLDepthPair(url, 0);
        Crawler crawler = new Crawler(firstPair, maxDepth);

        crawler.run();

        crawler.getSites();
        System.exit(0);
    }
}