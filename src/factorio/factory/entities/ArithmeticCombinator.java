package factorio.factory.entities;

import factorio.factory.entities.common.DoubleConnectionEntity;
import factorio.object.ArithmeticCondition;
import factorio.object.ControlBehaviour;
import factorio.object.Position;
import factorio.object.Signal;

import static factorio.factory.SignalLibrary.get;

public final class ArithmeticCombinator extends DoubleConnectionEntity<ArithmeticCombinator> {

    public ArithmeticCombinator() {
        super();
        name = "arithmetic-combinator";
    }

    public ArithmeticCombinator(Position position) {
        this();
        this.position = position;
    }

    public ArithmeticCombinator(Float x, Float y) {
        this(new Position(x, y));
    }

    public ArithmeticCombinator(Float x, Float y, int direction) {
        this(x, y);
        this.direction = direction;
    }

    public ArithmeticCombinator setCondition(ArithmeticCondition condition) {
        if (control_behavior == null) control_behavior = new ControlBehaviour(condition);
        else control_behavior.arithmetic_conditions = condition;
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
        return setCondition(get(first_signal), get(second_signal), operation, get(output_signal));
    }

    public ArithmeticCombinator setCondition(String first_signal, Integer second_constant, String operation, String output_signal) {
        return setCondition(get(first_signal), second_constant, operation, get(output_signal));
    }

    public ArithmeticCombinator setCondition(Integer first_constant, String second_signal, String operation, String output_signal) {
        return setCondition(first_constant, get(second_signal), operation, get(output_signal));
    }

    public ArithmeticCombinator setCondition(Integer first_constant, Integer second_constant, String operation, String output_signal) {
        return setCondition(first_constant, second_constant, operation, get(output_signal));
    }
}
