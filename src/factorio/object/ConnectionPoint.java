package factorio.object;

public class ConnectionPoint {
    public ConnectionData[] red;
    public ConnectionData[] green;
    
    public ConnectionPoint() {}
    
    public ConnectionPoint(ConnectionData[] red, ConnectionData[] green) {
        this.red = red;
        this.green = green;
    }

    public ConnectionData[] getRed() {
        return red;
    }

    public ConnectionPoint setRed(ConnectionData[] red) {
        this.red = red;
        return this;
    }

    public ConnectionData[] getGreen() {
        return green;
    }

    public ConnectionPoint setGreen(ConnectionData[] green) {
        this.green = green;
        return this;
    }
}
