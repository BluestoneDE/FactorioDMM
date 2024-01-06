package factorio.object;

import com.google.gson.annotations.Expose;
import factorio.factory.SignalLibrary;

public class Filter {
    @Expose
    private Signal signal;
    @Expose
    private int count;
    @Expose
    private int index;

    public Filter() {}

    public Filter(String signal, int count, int index) {
        this(SignalLibrary.get(signal), count, index);
    }

    public Filter(Signal signal, int count, int index) {
        setSignal(signal);
        setCount(count);
        setIndex(index);
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
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
