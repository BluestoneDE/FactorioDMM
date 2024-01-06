package factorio.object;

import com.google.gson.annotations.Expose;

public final class DeciderCondition {
    @Expose
    private Signal first_signal;
    @Expose
    private Signal second_signal;
    @Expose
    private Integer constant;
    @Expose
    private String comparator;
    @Expose
    private Signal output_signal;
    @Expose
    private boolean copy_count_from_input;

    public DeciderCondition() {}

    public DeciderCondition(
            Signal first_signal,
            Signal second_signal,
            String comparator,
            Signal output_signal,
            boolean copy_count_from_input
    ) {
        setFirstSignal(first_signal);
        setSecondSignal(second_signal);
        setComparator(comparator);
        setOutputSignal(output_signal);
        setCopyCountFromInput(copy_count_from_input);
    }

    public DeciderCondition(
            Signal first_signal,
            Integer constant,
            String comparator,
            Signal output_signal,
            boolean copy_count_from_input
    ) {
        setFirstSignal(first_signal);
        setConstant(constant);
        setComparator(comparator);
        setOutputSignal(output_signal);
        setCopyCountFromInput(copy_count_from_input);
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

    public Signal getOutputSignal() {
        return output_signal;
    }

    public void setOutputSignal(Signal output_signal) {
        this.output_signal = output_signal;
    }

    public boolean isCopyCountFromInput() {
        return copy_count_from_input;
    }

    public void setCopyCountFromInput(boolean copy_count_from_input) {
        this.copy_count_from_input = copy_count_from_input;
    }
}
