package lab8;

import java.util.LinkedList;


public class URLPool {
    public LinkedList<URLDepthPair> waitingURLs;
    public LinkedList<URLDepthPair> processedURLs;
    public int maxDepth;
    public int waitingThreads;

    public URLPool(URLDepthPair pair, int maxDepth) {
        waitingURLs = new LinkedList<URLDepthPair>();
        processedURLs = new LinkedList<URLDepthPair>();
        waitingURLs.add(pair);
        processedURLs.add(pair);
        this.maxDepth = maxDepth;
        this.waitingThreads = 0;
    }

    /**
     * Получить первый элемент из waitingUrls
     */
    public synchronized URLDepthPair get() throws InterruptedException {
        while (waitingURLs.size() == 0) {
            waitingThreads++;
            wait();
            waitingThreads--;
        }

        return waitingURLs.removeFirst();
    }

    /**
     * Добавить пару к waitingUrls
     */
    public synchronized void put(URLDepthPair pair) {
        processedURLs.addLast(pair);

        if (pair.depth < maxDepth) {
            waitingURLs.addLast(pair);
            notify();
        }
    }

    /**
     * Количество потоков
     */
    public synchronized int getWaitCount() {
        return waitingThreads;
    }

    /**
     * Вывод всех сайтов
     */
    public void getSites() {
        while (!processedURLs.isEmpty()) {
            System.out.println(processedURLs.removeFirst().toString());
        }
    }
}