package factorio.object;

public final class DeciderCondition {
    public SignalID first_signal;
    public SignalID second_signal;
    public Integer constant;
    public String comparator;
    public SignalID output_signal;
    public boolean copy_count_from_input;

    public DeciderCondition() {}

    public DeciderCondition(
            SignalID first_signal,
            SignalID second_signal,
            String comparator,
            SignalID output_signal,
            boolean copy_count_from_input
    ) {
        this.first_signal = first_signal;
        this.second_signal = second_signal;
        this.comparator = comparator;
        this.output_signal = output_signal;
        this.copy_count_from_input = copy_count_from_input;
    }

    public DeciderCondition(
            SignalID first_signal,
            Integer constant,
            String comparator,
            SignalID output_signal,
            boolean copy_count_from_input
    ) {
        this.first_signal = first_signal;
        this.constant = constant;
        this.comparator = comparator;
        this.output_signal = output_signal;
        this.copy_count_from_input = copy_count_from_input;
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

    public SignalID getOutputSignal() {
        return output_signal;
    }

    public void setOutputSignal(SignalID output_signal) {
        this.output_signal = output_signal;
    }

    public boolean isCopyCountFromInput() {
        return copy_count_from_input;
    }

    public void setCopyCountFromInput(boolean copy_count_from_input) {
        this.copy_count_from_input = copy_count_from_input;
    }
}
