package factorio.object;

import com.google.gson.annotations.Expose;

public final class Icon {
    @Expose
    private Integer index;
    @Expose
    private Signal signal;

    public Icon() {}

    public Icon(Integer index, Signal signal) {
        setIndex(index);
        setSignal(signal);
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }
}
