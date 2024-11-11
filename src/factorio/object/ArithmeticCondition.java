package factorio.object;

import com.google.gson.annotations.Expose;

public final class ArithmeticCondition {
    @Expose
    public Signal first_signal;
    @Expose
    public Integer first_constant;
    @Expose
    public Signal second_signal;
    @Expose
    public Integer second_constant;
    @Expose
    public Operation operation;
    @Expose
    public Signal output_signal;

    public ArithmeticCondition() {}

    public ArithmeticCondition(Signal first_signal, Signal second_signal, Operation operation, Signal output_signal) {
        this.first_signal = first_signal;
        this.second_signal = second_signal;
        this.operation = operation;
        this.output_signal = output_signal;
    }

    public ArithmeticCondition(Signal first_signal, Integer second_constant, Operation operation, Signal output_signal) {
        this.first_signal = first_signal;
        this.second_constant = second_constant;
        this.operation = operation;
        this.output_signal = output_signal;
    }

    public ArithmeticCondition(Integer first_constant, Signal second_signal, Operation operation, Signal output_signal) {
        this.first_constant = first_constant;
        this.second_signal = second_signal;
        this.operation = operation;
        this.output_signal = output_signal;
    }

    public ArithmeticCondition(Integer first_constant, Integer second_constant, Operation operation, Signal output_signal) {
        this.first_constant = first_constant;
        this.second_constant = second_constant;
        this.operation = operation;
        this.output_signal = output_signal;
    }
}

