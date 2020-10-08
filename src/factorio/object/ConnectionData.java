package factorio.object;

public final class ConnectionData {
    public int entity_id;
    public int circuit_id;

    public ConnectionData() {}

    public ConnectionData(int entity_id, int circuit_id) {
        this.entity_id = entity_id;
        this.circuit_id = circuit_id;
    }

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public int getCircuit_id() {
        return circuit_id;
    }

    public void setCircuit_id(int circuit_id) {
        this.circuit_id = circuit_id;
    }
}
