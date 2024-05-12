package factorio.object;

import com.google.gson.annotations.Expose;

public final class Position {
    @Expose
    public Float x;
    @Expose
    public Float y;

    public Position() {}

    public Position(Float x, Float  y) {
        this.x = x;
        this.y = y;
    }
}
