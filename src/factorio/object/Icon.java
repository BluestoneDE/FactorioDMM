package factorio.object;

public final class Icon {
    public Integer index;
    public SignalID signal;

    public Icon() {}

    public Icon(Integer index, SignalID signal) {
        this.index = index;
        this.signal = signal;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public SignalID getSignal() {
        return signal;
    }

    public void setSignal(SignalID signal) {
        this.signal = signal;
    }
}
