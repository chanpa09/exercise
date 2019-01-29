package park.test.wikisovereignflags;

import java.io.Serializable;

public class SovereignFlag implements Serializable {
    private String name;
    private String shortname;
    private String code;
    private String flag;
    private String korname;

    private int rid;

    public SovereignFlag(String korname, String shortname, String code, String name, int rid) {
        this.korname = korname;
        this.shortname = shortname;
        this.code = code;
        this.name = name;
        this.rid = rid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getShortname() {
        return this.shortname;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setKorname(String korname) {
        this.korname = korname;
    }

    public String getKorname() {
        return this.korname;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getRid() {
        return this.rid;
    }


}
