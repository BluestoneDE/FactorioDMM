package factorio.object;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public final class ConnectionPoint {
    @Expose
    public ArrayList<ConnectionData> red;
    @Expose
    public ArrayList<ConnectionData> green;
    
    public ConnectionPoint() {}
    
    public ConnectionPoint(ArrayList<ConnectionData> red, ArrayList<ConnectionData> green) {
        this.red = red;
        this.green = green;
    }
}
