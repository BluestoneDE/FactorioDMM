package factorio.object;

import com.google.gson.annotations.SerializedName;

public final class Connection {
    @SerializedName("1")
    public ConnectionPoint p1;
    @SerializedName("2")
    public ConnectionPoint p2;

    public Connection() {}

    public Connection(ConnectionPoint p1) {
        this.p1 = p1;
    }

    public Connection(ConnectionPoint p1, ConnectionPoint p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public ConnectionPoint get1() {
        return p1;
    }

    public void set1(ConnectionPoint p1) {
        this.p1 = p1;
    }

    public ConnectionPoint get2() {
        return p2;
    }

    public void set2(ConnectionPoint p2) {
        this.p2 = p2;
    }
}
