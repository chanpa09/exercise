package park.test.searchphotos;

public class ItemUtil {
    private static String SEARCH_URL = "https://secure.flickr.com/services/rest/?method=flickr.photos.search";
    private static String API_KEY = "&api_key=fd19050e7f615ac6d9742a1973d5a68d";
    private static String PER_PAGE = "&per_page=50";
    private static String SORT = "&sort=interestingness-desc";
    private static String FORMAT = "&format=json";
    private static String CONTECT_TYPE = "&content_type=1";
    private static String SEARCH_TEXT = "&text=";
    public static String REQUEST_URL = SEARCH_URL + API_KEY + PER_PAGE + SORT + FORMAT + CONTECT_TYPE + SEARCH_TEXT;
}
