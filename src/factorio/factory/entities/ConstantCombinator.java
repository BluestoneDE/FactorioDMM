package factorio.factory.entities;

import factorio.factory.entities.common.SingularConnectionEntity;
import factorio.object.ControlBehaviour;
import factorio.object.Filter;
import factorio.object.Position;
import factorio.object.Signal;

import java.util.ArrayList;

public final class ConstantCombinator extends SingularConnectionEntity<ConstantCombinator> {
    public final static int maxSignals = 20;

    public ConstantCombinator() {
        super();
        name = Signal.CONSTANT_COMBINATOR.name;
    }

    public ConstantCombinator(Position position) {
        this();
        this.position = position;
    }

    public ConstantCombinator(Float x, Float y) {
        this(new Position(x, y));
    }

    public ConstantCombinator(Float x, Float y, int direction) {
        this(x, y);
        this.direction = direction;
    }

    public ConstantCombinator setFilters(ArrayList<Filter> filters) {
        if (control_behavior == null) control_behavior = new ControlBehaviour(filters);
        else control_behavior.filters = filters;
        return this;
    }

    public ConstantCombinator addFilter(Filter filter) {
        if (control_behavior == null) setFilters(new ArrayList<>());
        control_behavior.filters.add(filter);
        return this;
    }

    public ConstantCombinator addFilter(Signal signal, int count, int index) {
        return addFilter(new Filter(signal, count, index));
    }
}
