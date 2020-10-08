package factorio.object;

public final class Icon {
    public int index;
    public SignalID signal;

    public Icon() {}

    public Icon(int index, SignalID signal) {
        this.index = index;
        this.signal = signal;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public SignalID getSignal() {
        return signal;
    }

    public void setSignal(SignalID signal) {
        this.signal = signal;
    }
}
