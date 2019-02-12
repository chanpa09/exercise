package park.test.toptracklist;

public class Track {

    private String trackname = "";
    private String trackurl = "";
    private String artistname = "";
    private String artistmbid = "";
    private String artisturl = "";
    private String imagesmallurl = "";
    private String imagemediumrul = "";
    private String imagelargeurl = "";
    private String imageextralargeurl = "";
    private int rank = 0;

    public Track() {
        super();
    }

    public Track(String trackname, String trackurl, String artistname, String artistmbid, String artisturl, String imagesmallurl, String imagemediumrul, String imagelargeurl, String imageextralargeurl, int rank) {
        this.trackname = trackname;
        this.trackurl = trackurl;
        this.artistname = artistname;
        this.artistmbid = artistmbid;
        this.artisturl = artisturl;
        this.imagesmallurl = imagesmallurl;
        this.imagemediumrul = imagemediumrul;
        this.imagelargeurl = imagelargeurl;
        this.imageextralargeurl = imageextralargeurl;
        this.rank = rank;
    }

    public String getTrackname() {
        return trackname;
    }

    public void setTrackname(String trackname) {
        this.trackname = trackname;
    }

    public String getTrackurl() {
        return trackurl;
    }

    public void setTrackurl(String trackurl) {
        this.trackurl = trackurl;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getArtistmbid() {
        return artistmbid;
    }

    public void setArtistmbid(String artistmbid) {
        this.artistmbid = artistmbid;
    }

    public String getArtisturl() {
        return artisturl;
    }

    public void setArtisturl(String artisturl) {
        this.artisturl = artisturl;
    }

    public String getImagesmallurl() {
        return imagesmallurl;
    }

    public void setImagesmallurl(String imagesmallurl) {
        this.imagesmallurl = imagesmallurl;
    }

    public String getImagemediumrul() {
        return imagemediumrul;
    }

    public void setImagemediumrul(String imagemediumrul) {
        this.imagemediumrul = imagemediumrul;
    }

    public String getImagelargeurl() {
        return imagelargeurl;
    }

    public void setImagelargeurl(String imagelargeurl) {
        this.imagelargeurl = imagelargeurl;
    }

    public String getImageextralargeurl() {
        return imageextralargeurl;
    }

    public void setImageextralargeurl(String imageextralargeurl) {
        this.imageextralargeurl = imageextralargeurl;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
