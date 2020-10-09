package factorio.object;

public final class CircuitCondition {
    public SignalID first_signal;
    public SignalID second_signal;
    public Integer constant;
    public String comparator;

    public CircuitCondition() {
    }

    public CircuitCondition(SignalID first_signal, SignalID second_signal, String comparator) {
        this.first_signal = first_signal;
        this.second_signal = second_signal;
        this.comparator = comparator;
    }

    public CircuitCondition(SignalID first_signal, Integer constant, String comparator) {
        this.first_signal = first_signal;
        this.constant = constant;
        this.comparator = comparator;
    }

    public SignalID getFirstSignal() {
        return first_signal;
    }

    public void setFirstSignal(SignalID first_signal) {
        this.first_signal = first_signal;
    }

    public SignalID getSecondSignal() {
        return second_signal;
    }

    public void setSecondSignal(SignalID second_signal) {
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
