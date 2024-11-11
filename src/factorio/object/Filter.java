package factorio.object;

import com.google.gson.annotations.Expose;
import factorio.object.types.Quality;

public class Filter {
    @Expose
    public int index = 1;
    @Expose
    public String type, name;
    @Expose
    public Quality quality = Quality.NORMAL;
    @Expose
    public String comparator = "=";
    @Expose
    public int count = 1;

    public Filter() {}

    public Filter(Signal signal, int count, int index) {
        this.index = index;
        this.type = signal.type;
        this.name = signal.name;
        this.count = count;
    }
}
