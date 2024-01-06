package factorio.factory.entities.common;

import factorio.object.Connection;
import factorio.object.ConnectionData;
import factorio.object.ConnectionPoint;
import factorio.object.Entity;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public abstract class DoubleConnectionEntity<E> extends Entity {

    private void setupConnections(boolean input, boolean red) {
        if (getConnections() == null) setConnections(new Connection());
        if (input) {
            if (getConnections().get1() == null) getConnections().set1(new ConnectionPoint());
            if (red) {
                if (getConnections().get1().getRed() == null) getConnections().get1().setRed(new ArrayList<>());
            } else if (getConnections().get1().getGreen() == null) getConnections().get1().setGreen(new ArrayList<>());
        } else {
            if (getConnections().get2() == null) getConnections().set2(new ConnectionPoint());
            if (red) {
                if (getConnections().get2().getRed() == null) getConnections().get2().setRed(new ArrayList<>());
            } else if (getConnections().get2().getGreen() == null) getConnections().get2().setGreen(new ArrayList<>());
        }
    }

    // using ConnectionData
    public E addRedInputConnection(ConnectionData connectionData) {
        setupConnections(true, true);
        getConnections().get1().getRed().add(connectionData);
        return (E) this;
    }

    public E addRedOutputConnection(ConnectionData connectionData) {
        setupConnections(false, true);
        getConnections().get2().getRed().add(connectionData);
        return (E) this;
    }

    public E addGreenInputConnection(ConnectionData connectionData) {
        setupConnections(true, false);
        getConnections().get1().getGreen().add(connectionData);
        return (E) this;
    }

    public E addGreenOutputConnection(ConnectionData connectionData) {
        setupConnections(false, false);
        getConnections().get2().getGreen().add(connectionData);
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
