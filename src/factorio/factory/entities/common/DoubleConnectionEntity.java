package factorio.factory.entities.common;

import factorio.object.*;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public abstract class DoubleConnectionEntity<E> extends Entity {

    private void setupConnections(boolean red, boolean input) {
        if (connections == null) connections = new Connection(null, null);
        if (input) {
            if (connections.p1 == null) connections.p1 = new ConnectionPoint();
            if (red) {
                if (connections.p1.red == null) connections.p1.red = new ArrayList<>();
            } else if (connections.p1.green == null) connections.p1.green = new ArrayList<>();
        } else {
            if (connections.p2 == null) connections.p2 = new ConnectionPoint();
            if (red) {
                if (connections.p2.red == null) connections.p2.red = new ArrayList<>();
            } else if (connections.p2.green == null) connections.p2.green = new ArrayList<>();
        }
    }

    public E setPosition(Float x, Float y) {
        this.position = new Position(x, y);
        return (E) this;
    }

    // using ConnectionData
    public E addRedInputConnection(ConnectionData connectionData) {
        setupConnections(true, true);
        connections.p1.red.add(connectionData);
        return (E) this;
    }

    public E addRedOutputConnection(ConnectionData connectionData) {
        setupConnections(true, false);
        connections.p2.red.add(connectionData);
        return (E) this;
    }

    public E addGreenInputConnection(ConnectionData connectionData) {
        setupConnections(false, true);
        connections.p1.green.add(connectionData);
        return (E) this;
    }

    public E addGreenOutputConnection(ConnectionData connectionData) {
        setupConnections(false, false);
        connections.p2.green.add(connectionData);
        return (E) this;
    }

    // using entity_id
    public E addRedInputConnection(int entity_id) {
        return addRedInputConnection(new ConnectionData(entity_id));
    }

    public E addRedOutputConnection(int entity_id) {
        return addRedOutputConnection(new ConnectionData(entity_id));
    }

    public E addGreenInputConnection(int entity_id) {
        return addGreenInputConnection(new ConnectionData(entity_id));
    }

    public E addGreenOutputConnection(int entity_id) {
        return addGreenOutputConnection(new ConnectionData(entity_id));
    }
}
