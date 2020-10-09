package factorio.object;

public final class Entity {
    private static int entity_count;
    public final int entity_number;
    public String name;
    public Position position;
    public Integer direction;
    public Float orientation;
    public Connection connections;
    public ControlBehaviour control_behavior;

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
            ControlBehaviour control_behavior
    ) {
        entity_count ++;
        this.entity_number = entity_count;
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.orientation = orientation;
        this.connections = connections;
        this.control_behavior = control_behavior;
    }

    public static void resetEntityCount() {
        entity_count = 0;
    }

    public static int getEntity_count() {
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
}
