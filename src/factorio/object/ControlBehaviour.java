package factorio.object;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public final class ControlBehaviour {
    @Expose
    public Boolean use_colors;
    @Expose
    public CircuitCondition circuit_condition;
    @Expose
    public ArithmeticCondition arithmetic_conditions;
    @Expose
    public DeciderCondition decider_conditions;
    @Expose
    public ArrayList<Filter> filters;

    public ControlBehaviour() {}

    public ControlBehaviour(boolean use_colors, CircuitCondition circuit_condition) {
        this.use_colors = use_colors;
        this.circuit_condition = circuit_condition;
    }

    public ControlBehaviour(ArithmeticCondition arithmetic_conditions) {
        this.arithmetic_conditions = arithmetic_conditions;
    }

    public ControlBehaviour(DeciderCondition decider_conditions) {
        this.decider_conditions = decider_conditions;
    }

    public ControlBehaviour(ArrayList<Filter> filters) {
        this.filters = filters;
    }
}
