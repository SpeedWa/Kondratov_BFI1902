/**
 *	Данный класс содержит информацию о текущей ссылки и ее глубине в поиске
 */
public class URLDepthPair {
    public static final String url_PREFIX = "http://";

    public String url;
    public int depth;

    public URLDepthPair(String url, int depth) {
        this.url = url;
        this.depth = depth;
    }

    /**
     *	Для вывода информации о ссылке и глубине
     */
    public String toString() {
        return "url: " + url + ", Depth: " + depth;
    }

    /**
     * Проверка на то что ссылка начинается с http://
     */
    public boolean isValid() {
        if (url.indexOf(url_PREFIX) == 0) return true;
        else return false;
    }

    /**
     * Возвращает часть ссылки без http://sitename.domain/
     */
    public String getDocPath() {
        int start = url_PREFIX.length();
        int end = url.indexOf('/', start);
        if (end == -1) return "/";
        else return url.substring(end);
    }

    /**
     * Возвращает название сайта по ссылке
     */
    public String getWebHost() {
        int start = url_PREFIX.length();
        int end = url.indexOf('/', start);
        if (end == -1) return url.substring(start);
        else return url.substring(start, end);
    }
}
