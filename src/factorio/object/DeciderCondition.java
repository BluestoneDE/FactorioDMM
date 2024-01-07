package factorio.object;

import com.google.gson.annotations.Expose;

public final class DeciderCondition {
    @Expose
    public Signal first_signal;
    @Expose
    public Signal second_signal;
    @Expose
    public Integer constant;
    @Expose
    public Comparator comparator;
    @Expose
    public Signal output_signal;
    @Expose
    public boolean copy_count_from_input;

    public DeciderCondition() {}

    public DeciderCondition(
            Signal first_signal,
            Signal second_signal,
            Comparator comparator,
            Signal output_signal,
            boolean copy_count_from_input
    ) {
        this.first_signal = first_signal;
        this.second_signal = second_signal;
        this.comparator = comparator;
        this.output_signal = output_signal;
        this.copy_count_from_input = copy_count_from_input;
    }

    public DeciderCondition(
            Signal first_signal,
            Integer constant,
            Comparator comparator,
            Signal output_signal,
            boolean copy_count_from_input
    ) {
        this.first_signal = first_signal;
        this.constant = constant;
        this.comparator = comparator;
        this.output_signal = output_signal;
        this.copy_count_from_input = copy_count_from_input;
    }
}
