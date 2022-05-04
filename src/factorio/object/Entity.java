package factorio.object;

import java.util.ArrayList;

public final class Entity {
    private static int entity_count;
    public final int entity_number;
    public String name;
    public Position position;
    public Integer direction;
    public Float orientation;
    public ControlBehaviour control_behavior;
    public Connection connections;
    public ArrayList<Integer> neighbours;

    public Entity() {
        entity_count ++;
        this.entity_number = entity_count;
    }

    public Entity(String name, Position position) {
        this();
        setName(name);
        setPosition(position);
    }

    public Entity(
            String name,
            Position position,
            Integer direction,
            ControlBehaviour controlBehavior,
            Connection connections
            ) {
        this(name, position);
        setDirection(direction);
        setControlBehavior(controlBehavior);
        setConnections(connections);
    }

    public static void setEntityCount(int entity_count) {
        Entity.entity_count = entity_count;
    }

    public static int getEntityCount() {
        return entity_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Float getOrientation() {
        return orientation;
    }

    public void setOrientation(Float orientation) {
        this.orientation = orientation;
    }

    public Connection getConnections() {
        return connections;
    }

    public void setConnections(Connection connections) {
        this.connections = connections;
    }

    public ControlBehaviour getControlBehavior() {
        return control_behavior;
    }

    public void setControlBehavior(ControlBehaviour control_behaviour) {
        this.control_behavior = control_behaviour;
    }

    public ArrayList<Integer> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Integer> neighbours) {
        this.neighbours = new ArrayList<Integer>() {{
            addAll(neighbours);
        }};
    }
}
