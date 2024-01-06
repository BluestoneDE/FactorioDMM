package factorio.object;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public final class ConnectionPoint {
    @Expose
    private ArrayList<ConnectionData> red;
    @Expose
    private ArrayList<ConnectionData> green;
    
    public ConnectionPoint() {}
    
    public ConnectionPoint(ArrayList<ConnectionData> red, ArrayList<ConnectionData> green) {
        setRed(red);
        setGreen(green);
    }

    public ArrayList<ConnectionData> getRed() {
        return red;
    }

    public ConnectionPoint setRed(ArrayList<ConnectionData> red) {
        this.red = red;
        return this;
    }

    public ArrayList<ConnectionData> getGreen() {
        return green;
    }

    public ConnectionPoint setGreen(ArrayList<ConnectionData> green) {
        this.green = green;
        return this;
    }
}
