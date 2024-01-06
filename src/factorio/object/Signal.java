package factorio.object;

import com.google.gson.annotations.Expose;

public final class Signal {
    @Expose
    private String name;
    @Expose
    private String type;

    public Signal() {}

    public Signal(String name, String type) {
        setName(name);
        setType(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
