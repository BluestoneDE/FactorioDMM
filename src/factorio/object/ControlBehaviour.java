package factorio.object;

public final class ControlBehaviour {
    public boolean use_colors;
    public CircuitCondition circuit_condition;
    public ArithmeticCondition arithmetic_conditions;
    public DeciderCondition decider_conditions;

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

    public boolean isUseColors() {
        return use_colors;
    }

    public void setUseColors(boolean use_colors) {
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
}
