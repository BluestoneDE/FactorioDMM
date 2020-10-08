package factorio.object;

public final class ArithmeticCondition {
    public SignalID first_signal;
    public int first_constant;
    public SignalID second_signal;
    public int second_constant;
    public String operation;
    public SignalID output_signal;

    public ArithmeticCondition() {}

    public ArithmeticCondition(SignalID first_signal, SignalID second_signal, String operation, SignalID output_signal) {
        this.first_signal = first_signal;
        this.second_signal = second_signal;
        this.operation = operation;
        this.output_signal = output_signal;
    }

    public ArithmeticCondition(SignalID first_signal, int second_constant, String operation, SignalID output_signal) {
        this.first_signal = first_signal;
        this.second_constant = second_constant;
        this.operation = operation;
        this.output_signal = output_signal;
    }

    public ArithmeticCondition(int first_constant, int second_constant, String operation, SignalID output_signal) {
        this.first_constant = first_constant;
        this.second_constant = second_constant;
        this.operation = operation;
        this.output_signal = output_signal;
    }

    public ArithmeticCondition(int first_constant, SignalID second_signal, String operation, SignalID output_signal) {
        this.first_constant = first_constant;
        this.second_signal = second_signal;
        this.operation = operation;
        this.output_signal = output_signal;
    }

    public SignalID getFirstSignal() {
        return first_signal;
    }

    public void setFirstSignal(SignalID first_signal) {
        this.first_signal = first_signal;
    }

    public int getFirstConstant() {
        return first_constant;
    }

    public void setFirstConstant(int first_constant) {
        this.first_constant = first_constant;
    }

    public SignalID getSecondSignal() {
        return second_signal;
    }

    public void setSecondSignal(SignalID second_signal) {
        this.second_signal = second_signal;
    }

    public int getSecondConstant() {
        return second_constant;
    }

    public void setSecondConstant(int second_constant) {
        this.second_constant = second_constant;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public SignalID getOutputSignal() {
        return output_signal;
    }

    public void setOutputSignal(SignalID output_signal) {
        this.output_signal = output_signal;
    }
}
