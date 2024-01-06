package factorio.factory.entities;

import factorio.factory.SignalLibrary;
import factorio.factory.entities.common.DoubleConnectionEntity;
import factorio.object.*;

import java.util.ArrayList;

public final class ArithmeticCombinator extends DoubleConnectionEntity<ArithmeticCombinator> {

    public ArithmeticCombinator() {
        super();
        setName("arithmetic-combinator");
    }

    public ArithmeticCombinator(Position position) {
        this();
        setPosition(position);
    }

    public ArithmeticCombinator(Float x, Float y) {
        this(new Position(x, y));
    }

    public ArithmeticCombinator(Float x, Float y, int direction) {
        this(x, y);
        setDirection(direction);
    }

    public ArithmeticCombinator setCondition(ArithmeticCondition condition) {
        if (getControlBehavior() == null) setControlBehavior(new ControlBehaviour(condition));
        else getControlBehavior().setArithmeticConditions(condition);
        return this;
    }
    public ArithmeticCombinator setCondition(Signal first_signal, Signal second_signal, String operation, Signal output_signal) {
        return setCondition(new ArithmeticCondition(first_signal, second_signal, operation, output_signal));
    }
    public ArithmeticCombinator setCondition(Signal first_signal, Integer second_constant, String operation, Signal output_signal) {
        return setCondition(new ArithmeticCondition(first_signal, second_constant, operation, output_signal));
    }
    public ArithmeticCombinator setCondition(Integer first_constant, Signal second_signal, String operation, Signal output_signal) {
        return setCondition(new ArithmeticCondition(first_constant, second_signal, operation, output_signal));
    }
    public ArithmeticCombinator setCondition(Integer first_constant, Integer second_constant, String operation, Signal output_signal) {
        return setCondition(new ArithmeticCondition(first_constant, second_constant, operation, output_signal));
    }

    // using SignalLibrary with String
    public ArithmeticCombinator setCondition(String first_signal, String second_signal, String operation, String output_signal) {
        return setCondition(SignalLibrary.get(first_signal), SignalLibrary.get(second_signal), operation, SignalLibrary.get(output_signal));
    }
    public ArithmeticCombinator setCondition(String first_signal, Integer second_constant, String operation, String output_signal) {
        return setCondition(SignalLibrary.get(first_signal), second_constant, operation, SignalLibrary.get(output_signal));
    }
    public ArithmeticCombinator setCondition(Integer first_constant, String second_signal, String operation, String output_signal) {
        return setCondition(first_constant, SignalLibrary.get(second_signal), operation, SignalLibrary.get(output_signal));
    }
    public ArithmeticCombinator setCondition(Integer first_constant, Integer second_constant, String operation, String output_signal) {
        return setCondition(first_constant, second_constant, operation, SignalLibrary.get(output_signal));
    }
}
