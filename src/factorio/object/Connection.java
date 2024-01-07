package factorio.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Connection {

    @Expose @SerializedName("1")
    public ConnectionPoint p1;

    @Expose @SerializedName("2")
    public ConnectionPoint p2;

    public Connection() {}

    public Connection(ConnectionPoint p1) {
        this.p1 = p1;
    }

    public Connection(ConnectionPoint p1, ConnectionPoint p2) {
        this(p1);
        this.p2 = p2;
    }
}
