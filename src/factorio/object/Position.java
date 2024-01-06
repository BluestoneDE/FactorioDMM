package factorio.object;

import com.google.gson.annotations.Expose;

public final class Position {
    @Expose
    private Float x;
    @Expose
    private Float y;

    public Position() {}

    public Position(Float x, Float  y) {
        setX(x);
        setY(y);
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }
}
