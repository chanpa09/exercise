package park.test.musicmainactivity;

public class MusicUtil {
    public static String APIKEY="6739db0b4a1d5d08bd117bc624eb6e7c";
    public static String BILLBOARD="https://www.billboard.com/charts/hot-100/";
    public static String BILLBOARD200="https://www.billboard.com/charts/billboard-200/";
    public static String BILLBOARDARTIST="https://www.billboard.com/charts/greatest-billboard-200-artists";

    public static String FM="http://ws.audioscrobbler.com/2.0/";
    public static String FORMAT="&format=json";
    public static String METHOD1="?method=tag.gettoptracks";
    public static String METHOD2="?method=artist.gettoptracks";
    public static int LINE=50;
    public static String LIMIT="&limit="+LINE;
    public static String TAG="&tag=";
    public static String ARTIST="&artist=";
    public static String TOPTRACK=FM+METHOD1+FORMAT+LIMIT+"&api_key="+APIKEY+TAG;
    public static String TOPARTIST=FM+METHOD2+FORMAT+LIMIT+"&api_key="+APIKEY+ARTIST;
}
