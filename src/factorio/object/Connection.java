package factorio.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Connection {

    @Expose @SerializedName("1")
    private ConnectionPoint p1;

    @Expose @SerializedName("2")
    private ConnectionPoint p2;

    public Connection() {}

    public Connection(ConnectionPoint p1) {
        set1(p1);
    }

    public Connection(ConnectionPoint p1, ConnectionPoint p2) {
        this(p1);
        set2(p2);
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
