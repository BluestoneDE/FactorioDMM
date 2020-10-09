package factorio.object;

public final class Entity {
    private static int entity_count;
    public final int entity_number;
    public String name;
    public Position position;
    public Integer direction;
    public Float orientation;
    public Connection connections;
    public ControlBehaviour control_behaviour;

    public Entity() {
        entity_count ++;
        this.entity_number = entity_count;
    }

    public Entity(
            String name,
            Position position,
            Integer direction,
            Float orientation,
            Connection connections,
            ControlBehaviour control_behaviour
    ) {
        entity_count ++;
        this.entity_number = entity_count;
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.orientation = orientation;
        this.connections = connections;
        this.control_behaviour = control_behaviour;
    }

    public static void resetEntityCount() {
        entity_count = 0;
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

    public ControlBehaviour getControlBehaviour() {
        return control_behaviour;
    }

    public void setControlBehaviour(ControlBehaviour control_behaviour) {
        this.control_behaviour = control_behaviour;
    }
}
