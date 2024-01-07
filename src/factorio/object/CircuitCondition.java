package factorio.object;

import com.google.gson.annotations.Expose;

public final class CircuitCondition {
    @Expose
    public Signal first_signal;
    @Expose
    public Signal second_signal;
    @Expose
    public Integer constant;
    @Expose
    public String comparator;

    public CircuitCondition() {
    }

    public CircuitCondition(Signal first_signal) {
        this.first_signal = first_signal;
    }

    public CircuitCondition(Signal first_signal, Signal second_signal, String comparator) {
        this(first_signal);
        this.second_signal = second_signal;
        this.comparator = comparator;
    }

    public CircuitCondition(Signal first_signal, Integer constant, String comparator) {
        this(first_signal);
        this.constant = constant;
        this.comparator = comparator;
    }
}
