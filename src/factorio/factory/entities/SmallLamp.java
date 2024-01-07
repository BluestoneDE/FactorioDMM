package factorio.factory.entities;

import factorio.factory.entities.common.SingularConnectionEntity;
import factorio.object.CircuitCondition;
import factorio.object.ControlBehaviour;
import factorio.object.Position;
import factorio.object.Signal;

public class SmallLamp extends SingularConnectionEntity<SmallLamp> {
    public SmallLamp() {
        super();
        name = Signal.SMALL_LAMP.name;
    }

    public SmallLamp(Position position) {
        this();
        this.position = position;
    }

    public SmallLamp(Float x, Float y) {
        this(new Position(x, y));
    }

    public SmallLamp setCondition(boolean useColors, CircuitCondition condition) {
        if (control_behavior == null) control_behavior = new ControlBehaviour(useColors, condition);
        else {
            control_behavior.use_colors = useColors;
            control_behavior.circuit_condition = condition;
        }
        return this;
    }

    public SmallLamp setCondition(boolean useColors, Signal first_signal) {
        return setCondition(useColors, new CircuitCondition(first_signal));
    }

    public SmallLamp setCondition(boolean useColors, Signal first_signal, Signal second_signal, String comparator) {
        return setCondition(useColors, new CircuitCondition(first_signal, second_signal, comparator));
    }

    public SmallLamp setCondition(boolean useColors, Signal first_signal, Integer constant, String comparator) {
        return setCondition(useColors, new CircuitCondition(first_signal, constant, comparator));
    }
}
