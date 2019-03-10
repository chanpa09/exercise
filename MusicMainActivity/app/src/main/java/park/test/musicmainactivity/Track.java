package park.test.musicmainactivity;

import java.io.Serializable;

public class Track implements Serializable {
    private String trackname = "";
    private String trackurl = "";
    private String artistname = "";
    private String artistmbid = "";
    private String artisturl = "";
    private String imagesmallurl = "";
    private String imagemediumurl = "";
    private String imagelargeurl = "";
    private String imageextralargeurl = "";
    private int rank = 0;


    public Track(String trackname, String trackurl, String artistname, String artistmbid, String artisturl, String imagesmallurl, String imagemediumurl, String imagelargeurl, String imageextralargeurl, int rank) {
        this.trackname = trackname;
        this.trackurl = trackurl;
        this.artistname = artistname;
        this.artistmbid = artistmbid;
        this.artisturl = artisturl;
        this.imagesmallurl = imagesmallurl;
        this.imagemediumurl = imagemediumurl;
        this.imagelargeurl = imagelargeurl;
        this.imageextralargeurl = imageextralargeurl;
        this.rank = rank;
    }

    public Track(String trackname, String trackurl, String artistname, String artisturl, String imagesmallurl) {
        this.trackname = trackname;
        this.trackurl = trackurl;
        this.artistname = artistname;
        this.artisturl = artisturl;
        this.imagesmallurl = imagesmallurl;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackname='" + trackname + '\'' +
                ", trackurl='" + trackurl + '\'' +
                ", artistname='" + artistname + '\'' +
                ", artistmbid='" + artistmbid + '\'' +
                ", artisturl='" + artisturl + '\'' +
                ", imagesmallurl='" + imagesmallurl + '\'' +
                ", imagemediumurl='" + imagemediumurl + '\'' +
                ", imagelargeurl='" + imagelargeurl + '\'' +
                ", imageextralargeurl='" + imageextralargeurl + '\'' +
                ", rank=" + rank +
                '}';
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

    public String getImagemediumurl() {
        return imagemediumurl;
    }

    public void setImagemediumurl(String imagemediumurl) {
        this.imagemediumurl = imagemediumurl;
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