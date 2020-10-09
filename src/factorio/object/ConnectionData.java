package factorio.object;

public final class ConnectionData {
    public Integer entity_id;
    public Integer circuit_id;

    public ConnectionData() {}

    public ConnectionData(Integer entity_id) {
        this.entity_id = entity_id;
    }

    public ConnectionData(Integer entity_id, Integer circuit_id) {
        this.entity_id = entity_id;
        this.circuit_id = circuit_id;
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
