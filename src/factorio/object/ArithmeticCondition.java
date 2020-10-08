package factorio.object;

public class ArithmeticCondition {
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
}
