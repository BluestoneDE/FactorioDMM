package factorio.factory.entities.common;

import factorio.factory.entities.ConstantCombinator;
import factorio.object.*;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public abstract class SingularConnectionEntity<E> extends Entity {

    private void setupConnections(boolean red) {
        if (connections == null) connections = new Connection(new ConnectionPoint());
        if (red) {
            if (connections.p1.red == null) connections.p1.red = new ArrayList<>();
        } else if (connections.p1.green == null) connections.p1.green = new ArrayList<>();
    }

    public E setPosition(Float x, Float y) {
        this.position = new Position(x, y);
        return (E) this;
    }

    // using ConnectionData
    public E addRedConnection(ConnectionData connectionData) {
        setupConnections(true);
        connections.p1.red.add(connectionData);
        return (E) this;
    }

    public E addGreenConnection(ConnectionData connectionData) {
        setupConnections(false);
        connections.p1.green.add(connectionData);
        return (E) this;
    }

    // using entity_id
    public E addRedConnection(int entity_id) {
        return addRedConnection(new ConnectionData(entity_id));
    }

    public E addGreenConnection(int entity_id) {
        return addGreenConnection(new ConnectionData(entity_id));
    }
}
