package factorio.object;

import com.google.gson.annotations.Expose;

public final class ArithmeticCondition {
    @Expose
    private Signal first_signal;
    @Expose
    private Integer first_constant;
    @Expose
    private Signal second_signal;
    @Expose
    private Integer second_constant;
    @Expose
    private String operation;
    @Expose
    private Signal output_signal;

    public ArithmeticCondition() {}

    public ArithmeticCondition(Signal first_signal, Signal second_signal, String operation, Signal output_signal) {
        setFirstSignal(first_signal);
        setSecondSignal(second_signal);
        setOperation(operation);
        setOutputSignal(output_signal);
    }

    public ArithmeticCondition(Signal first_signal, Integer second_constant, String operation, Signal output_signal) {
        setFirstSignal(first_signal);
        setSecondConstant(second_constant);
        setOperation(operation);
        setOutputSignal(output_signal);
    }

    public ArithmeticCondition(Integer first_constant, Signal second_signal, String operation, Signal output_signal) {
        setFirstConstant(first_constant);
        setSecondSignal(second_signal);
        setOperation(operation);
        setOutputSignal(output_signal);
    }

    public ArithmeticCondition(Integer first_constant, Integer second_constant, String operation, Signal output_signal) {
        setFirstConstant(first_constant);
        setSecondConstant(second_constant);
        setOperation(operation);
        setOutputSignal(output_signal);
    }

    public Signal getFirstSignal() {
        return first_signal;
    }

    public void setFirstSignal(Signal first_signal) {
        this.first_signal = first_signal;
    }

    public Integer getFirstConstant() {
        return first_constant;
    }

    public void setFirstConstant(Integer first_constant) {
        this.first_constant = first_constant;
    }

    public Signal getSecondSignal() {
        return second_signal;
    }

    public void setSecondSignal(Signal second_signal) {
        this.second_signal = second_signal;
    }

    public Integer getSecondConstant() {
        return second_constant;
    }

    public void setSecondConstant(Integer second_constant) {
        this.second_constant = second_constant;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Signal getOutputSignal() {
        return output_signal;
    }

    public void setOutputSignal(Signal output_signal) {
        this.output_signal = output_signal;
    }
}
