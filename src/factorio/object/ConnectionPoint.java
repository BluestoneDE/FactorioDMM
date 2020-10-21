package factorio.object;

import java.util.ArrayList;

public final class ConnectionPoint {
    public ArrayList<ConnectionData> red;
    public ArrayList<ConnectionData> green;
    
    public ConnectionPoint() {}
    
    public ConnectionPoint(ArrayList<ConnectionData> red, ArrayList<ConnectionData> green) {
        this.red = red;
        this.green = green;
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
