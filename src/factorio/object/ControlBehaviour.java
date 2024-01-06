package factorio.object;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public final class ControlBehaviour {
    @Expose
    private Boolean use_colors;
    @Expose
    private CircuitCondition circuit_condition;
    @Expose
    private ArithmeticCondition arithmetic_conditions;
    @Expose
    private DeciderCondition decider_conditions;
    @Expose
    private ArrayList<Filter> filters;

    public ControlBehaviour() {}

    public ControlBehaviour(boolean use_colors, CircuitCondition circuit_condition) {
        setUseColors(use_colors);
        setCircuitCondition(circuit_condition);
    }

    public ControlBehaviour(ArithmeticCondition arithmetic_conditions) {
        setArithmeticConditions(arithmetic_conditions);
    }

    public ControlBehaviour(DeciderCondition decider_conditions) {
        setDeciderConditions(decider_conditions);
    }

    public ControlBehaviour(ArrayList<Filter> filters) {
        setFilters(filters);
    }

    public Boolean isUseColors() {
        return use_colors;
    }

    public void setUseColors(Boolean use_colors) {
        this.use_colors = use_colors;
    }

    public CircuitCondition getCircuitCondition() {
        return circuit_condition;
    }

    public void setCircuitCondition(CircuitCondition circuit_condition) {
        this.circuit_condition = circuit_condition;
    }

    public ArithmeticCondition getArithmeticConditions() {
        return arithmetic_conditions;
    }

    public void setArithmeticConditions(ArithmeticCondition arithmetic_conditions) {
        this.arithmetic_conditions = arithmetic_conditions;
    }

    public DeciderCondition getDeciderConditions() {
        return decider_conditions;
    }

    public void setDeciderConditions(DeciderCondition decider_conditions) {
        this.decider_conditions = decider_conditions;
    }

    public ArrayList<Filter> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<Filter> filters) {
        this.filters = filters;
    }
}
