package factorio.object;

import com.google.gson.annotations.Expose;

public final class ConnectionData {
    @Expose
    private Integer entity_id;
    @Expose
    private Integer circuit_id;

    public ConnectionData() {}

    public ConnectionData(Integer entity_id) {
        setEntity_id(entity_id);
        this.entity_id = entity_id;
    }

    public ConnectionData(Integer entity_id, Integer circuit_id) {
        this(entity_id);
        setCircuit_id(circuit_id);
    }

    public Integer getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(Integer entity_id) {
        this.entity_id = entity_id;
    }

    public Integer getCircuit_id() {
        return circuit_id;
    }

    public void setCircuit_id(Integer circuit_id) {
        this.circuit_id = circuit_id;
    }
}
