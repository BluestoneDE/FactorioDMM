package factorio.factory.entities;

import factorio.factory.SignalLibrary;
import factorio.factory.entities.common.SingularConnectionEntity;
import factorio.object.*;

public class SmallLamp extends SingularConnectionEntity<SmallLamp> {
    public SmallLamp() {
        super();
        setName("small-lamp");
    }

    public SmallLamp(Position position) {
        this();
        setPosition(position);
    }

    public SmallLamp(Float x, Float y) {
        this(new Position(x, y));
    }

    public SmallLamp setCondition(boolean useColors, CircuitCondition condition) {
        if (getControlBehavior() == null) setControlBehavior(new ControlBehaviour(useColors, condition));
        else {
            getControlBehavior().setUseColors(useColors);
            getControlBehavior().setCircuitCondition(condition);
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

    // using SignalLibrary with String
    public SmallLamp setCondition(boolean useColors, String first_signal) {
        return setCondition(useColors, SignalLibrary.get(first_signal));
    }
    public SmallLamp setCondition(boolean useColors, String first_signal, String second_signal, String comparator) {
        return setCondition(useColors, SignalLibrary.get(first_signal), SignalLibrary.get(second_signal), comparator);
    }
    public SmallLamp setCondition(boolean useColors, String first_signal, Integer constant, String comparator) {
        return setCondition(useColors, SignalLibrary.get(first_signal), constant, comparator);
    }
}
