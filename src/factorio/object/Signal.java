package factorio.object;

import com.google.gson.annotations.Expose;

public final class Signal {
    @Expose
    public String name;
    @Expose
    public String type;

    public Signal() {}

    public Signal(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
