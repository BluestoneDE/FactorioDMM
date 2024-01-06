package factorio.factory.entities;

import factorio.factory.SignalLibrary;
import factorio.factory.entities.common.SingularConnectionEntity;
import factorio.object.*;

import java.util.ArrayList;

public final class ConstantCombinator extends SingularConnectionEntity<ConstantCombinator> {
    public final static int maxSignals = 20;

    public ConstantCombinator() {
        super();
        setName("constant-combinator");
    }

    public ConstantCombinator(Position position) {
        this();
        setPosition(position);
    }

    public ConstantCombinator(Float x, Float y) {
        this(new Position(x, y));
    }

    public ConstantCombinator(Float x, Float y, int direction) {
        this(x, y);
        setDirection(direction);
    }

    public ConstantCombinator setFilters(ArrayList<Filter> filters) {
        if (getControlBehavior() == null) setControlBehavior(new ControlBehaviour(filters));
        else getControlBehavior().setFilters(filters);
        return this;
    }

    public ConstantCombinator addFilter(Filter filter) {
        if (getControlBehavior() == null) setFilters(new ArrayList<>());
        getControlBehavior().getFilters().add(filter);
        return this;
    }
    public ConstantCombinator addFilter(Signal signal, int count, int index) {
        return addFilter(new Filter(signal, count, index));
    }

    // using SignalLibrary with String
    public ConstantCombinator addFilter(String signal, int count, int index) {
        return addFilter(SignalLibrary.get(signal), count, index);
    }
}
