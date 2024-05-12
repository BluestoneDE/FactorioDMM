package factorio.object;

import com.google.gson.annotations.Expose;

public final class ConnectionData {
    @Expose
    public Integer entity_id;
    @Expose
    public Integer circuit_id;

    public ConnectionData() {}

    public ConnectionData(Integer entity_id) {
        this.entity_id = entity_id;
    }

    public ConnectionData(Integer entity_id, Integer circuit_id) {
        this(entity_id);
        this.circuit_id = circuit_id;
    }
}
