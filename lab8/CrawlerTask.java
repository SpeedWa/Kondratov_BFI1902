package lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class CrawlerTask implements Runnable {
    public static final String LINK_PREFIX = "<a href=\"";
    public URLPool pool;

    public CrawlerTask(URLPool pool) {
        this.pool = pool;
    }

    /**
     * Берет следующую пару элементов и вызывает их обработку
     */
    public void run() {
        while (true) {
            try {
                URLDepthPair nextpair = pool.get();
                unitWork(nextpair);
            }
            catch (InterruptedException e) {
                System.out.println("Произошло исключение " +
                        "InterruptedException");
            }
            catch (UnknownHostException e) {
                System.out.println("Произошло исключение " +
                        "UnknownHostException, ignoring...");
            }
            catch (IOException e) {
                System.out.println("Произошло исключение " +
                        "IOException...");
            }
        }
    }

    /**
     * Отправляет запрос.
     * Получает ответ.
     * Ищет все ссылки.
     * Добавляет их к ожидающим ссылкам.
     */
    public void unitWork(URLDepthPair pair) throws UnknownHostException, IOException {
        String webServer = pair.getWebHost();
        int webPort = 80;

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

            if (line == null) break;

            int idx = 0;

            while (true) {
                idx = line.indexOf(LINK_PREFIX, idx);

                if (idx == -1) break;

                int start = idx + LINK_PREFIX.length();
                int end = line.indexOf('"', start);

                if (end == -1) break;

                String newURL = line.substring(start, end);
                int newDepth = pair.depth + 1;

                URLDepthPair newPair = new URLDepthPair(newURL, newDepth);

                if (newPair.isValid()) {
                    pool.put(newPair);
                }

                idx = end + 1;
            }
        }

        socket.close();
    }
}