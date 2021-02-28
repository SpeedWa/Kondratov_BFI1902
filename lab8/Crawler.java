package lab8;

public class Crawler {
    public int maxDepth;
    public int numThreads;
    public URLPool pool;

    public Crawler(URLDepthPair pair, int maxDepth, int numThreads) {
        pool = new URLPool(pair, maxDepth);
        this.maxDepth = maxDepth;
        this.numThreads = numThreads;
    }

    /**
     * В начале принимаем аргументы из командной строки.
     * Затем проверяем корректность входных данных.
     * После чего запускаем цикл в котором запускаются потоки.
     * В конце происходит вывод сайтов.
     */
    public static void main(String[] args) {
        int maxDepth = 0;
        int numThreads = 0;

        if (args.length != 3) {
            System.out.println("Должно быть: java Crawler <URL> <depth> <number of threads>");
            System.exit(1);
        }

        String URL = args[0];

        try {
            maxDepth = Integer.parseInt(args[1]);

            if (maxDepth < 0) {
                System.out.println("Глубина должна быть больше 0");
                System.exit(1);
            }

            numThreads = Integer.parseInt(args[2]);

            if (numThreads <= 0) {
                System.out.println("Количество потоков должно быть больше 0");
                System.exit(1);
            }
        }
        catch (NumberFormatException nfe) {
            System.out.println("Должно быть: java Crawler <URL> <depth> <number of threads>");
            System.exit(1);
        }

        URLDepthPair firstPair = new URLDepthPair(URL, 0);

        Crawler crawler = new Crawler(firstPair, maxDepth, numThreads);

        for (int i = 0; i < numThreads; i++) {
            CrawlerTask ct = new CrawlerTask(crawler.pool);
            Thread t = new Thread(ct);
            t.start();
        }

        while (crawler.pool.getWaitCount() != crawler.numThreads) {
            try {
                Thread.sleep(100); // 0.1 second
            } catch (InterruptedException ie) {
                System.out.println("Исключение " +
                        "InterruptedException...");
            }
        }

        crawler.pool.getSites();
        System.exit(0);
    }
}