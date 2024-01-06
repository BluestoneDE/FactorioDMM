package factorio.factory.entities.common;

import factorio.object.Connection;
import factorio.object.ConnectionData;
import factorio.object.ConnectionPoint;
import factorio.object.Entity;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public abstract class SingularConnectionEntity<E> extends Entity {

    private void setupConnections(boolean red) {
        if (getConnections() == null) setConnections(new Connection(new ConnectionPoint()));
        if (red) {
            if (getConnections().get1().getRed() == null) getConnections().get1().setRed(new ArrayList<>());
        } else if (getConnections().get1().getGreen() == null) getConnections().get1().setGreen(new ArrayList<>());
    }

    // using ConnectionData
    public E addRedConnection(ConnectionData connectionData) {
        setupConnections(true);
        getConnections().get1().getRed().add(connectionData);
        return (E) this;
    }

    public E addGreenConnection(ConnectionData connectionData) {
        setupConnections(false);
        getConnections().get1().getGreen().add(connectionData);
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
