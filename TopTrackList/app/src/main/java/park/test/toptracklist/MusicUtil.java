package park.test.toptracklist;

public class MusicUtil {
    public static String APIKEY="6739db0b4a1d5d08bd117bc624eb6e7c";
    public static String FM="http://ws.audioscrobbler.com/2.0/";
    public static String FORMAT="&format=json";
    public static String METHOD1="?method=tag.gettoptracks";
    public static int LINE=50;
    public static String LIMIT="&limit="+LINE;
    public static String TAG="&tag=";
    public static String TOPTRACK=FM+METHOD1+FORMAT+LIMIT+"&api_key="+APIKEY+TAG;
}
