package factorio.object;

import com.google.gson.annotations.Expose;

public final class Icon {
    @Expose
    public Integer index;
    @Expose
    public Signal signal;

    public Icon() {}

    public Icon(Integer index, Signal signal) {
        this.index = index;
        this.signal = signal;
    }
}
