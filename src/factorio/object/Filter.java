package factorio.object;

public class Filter {
    public SignalID signal;
    public int count;
    public int index;

    public Filter() {}

    public Filter(SignalID signal, int count, int index) {
        this.signal = signal;
        this.count = count;
        this.index = index;
    }

    public SignalID getSignal() {
        return signal;
    }

    public void setSignal(SignalID signal) {
        this.signal = signal;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
