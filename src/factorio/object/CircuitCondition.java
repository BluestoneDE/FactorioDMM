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
        setFirstSignal(first_signal);
    }

    public CircuitCondition(Signal first_signal, Signal second_signal, String comparator) {
        this(first_signal);
        setSecondSignal(second_signal);
        setComparator(comparator);
    }

    public CircuitCondition(Signal first_signal, Integer constant, String comparator) {
        this(first_signal);
        setConstant(constant);
        setComparator(comparator);
    }

    public Signal getFirstSignal() {
        return first_signal;
    }

    public void setFirstSignal(Signal first_signal) {
        this.first_signal = first_signal;
    }

    public Signal getSecondSignal() {
        return second_signal;
    }

    public void setSecondSignal(Signal second_signal) {
        this.second_signal = second_signal;
    }

    public Integer getConstant() {
        return constant;
    }

    public void setConstant(Integer constant) {
        this.constant = constant;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }
}
