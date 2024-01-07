package factorio.factory.entities;

import factorio.factory.entities.common.DoubleConnectionEntity;
import factorio.object.*;

public final class ArithmeticCombinator extends DoubleConnectionEntity<ArithmeticCombinator> {

    public ArithmeticCombinator() {
        super();
        name = Signal.ARITHMETIC_COMBINATOR.name;
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

    public ArithmeticCombinator setCondition(Signal first_signal, Signal second_signal, Operation operation, Signal output_signal) {
        return setCondition(new ArithmeticCondition(first_signal, second_signal, operation, output_signal));
    }

    public ArithmeticCombinator setCondition(Signal first_signal, Integer second_constant, Operation operation, Signal output_signal) {
        return setCondition(new ArithmeticCondition(first_signal, second_constant, operation, output_signal));
    }

    public ArithmeticCombinator setCondition(Integer first_constant, Signal second_signal, Operation operation, Signal output_signal) {
        return setCondition(new ArithmeticCondition(first_constant, second_signal, operation, output_signal));
    }

    public ArithmeticCombinator setCondition(Integer first_constant, Integer second_constant, Operation operation, Signal output_signal) {
        return setCondition(new ArithmeticCondition(first_constant, second_constant, operation, output_signal));
    }
}
