package lab9;

public class URLDepthPair {
    public static final String URL_PREFIX = "http://";

    public String URL;
    public int depth;

    public URLDepthPair(String URL, int depth) {
        this.URL = URL;
        this.depth = depth;
    }

    /**
     * Преобразование к строке для вывода
     */
    public String toString() {
        return "URL: " + URL + ", Depth: " + depth;
    }

    /**
     * Проверка правильности url
     */
    public boolean isValid() {
        if (URL.indexOf(URL_PREFIX) == 0) return true;
        else return false;
    }

    /**
     * Вернуть docPath часть документа
     */
    public String getDocPath() {
        int start = URL_PREFIX.length();
        int end = URL.indexOf('/', start);
        if (end == -1) return "/";
        else return URL.substring(end);
    }

    /**
     * Вернуть webHost часть документа
     */
    public String getWebHost() {
        int start = URL_PREFIX.length();
        int end = URL.indexOf('/', start);
        if (end == -1) return URL.substring(start);
        else return URL.substring(start, end);
    }
}