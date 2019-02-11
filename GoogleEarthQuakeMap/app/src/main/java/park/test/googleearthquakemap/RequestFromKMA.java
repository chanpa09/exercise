package park.test.googleearthquakemap;

import java.util.ArrayList;

public class RequestFromKMA {

    private ArrayList<EarthQuake> jpEarthQuakes = new ArrayList<>();
    private boolean isConnection = false;

    public RequestFromKMA() {
        jpEarthQuakes.clear();
    }

    public ArrayList<EarthQuake> getJPEarthQuake() {
        return jpEarthQuakes;
    }

    public boolean isConnection() {
        return isConnection;
    }

    public void getEarthQuakes() {
        jpEarthQuakes.clear();

        isConnection = true;
    }

}
