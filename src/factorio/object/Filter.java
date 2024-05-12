package factorio.object;

import com.google.gson.annotations.Expose;

public class Filter {
    @Expose
    public Signal signal;
    @Expose
    public int count;
    @Expose
    public int index;

    public Filter() {}

    public Filter(Signal signal, int count, int index) {
        this.signal = signal;
        this.count = count;
        this.index = index;
    }
}
