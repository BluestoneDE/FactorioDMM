package factorio.object;

public final class Entity {
    private static int entity_count;
    public final int entity_number;
    public String name;
    public Position position;
    public int direction;
    public float orientation;
    public Connection connections;
    public ControlBehaviour control_behaviour;

    public Entity() {
        this.entity_number = entity_count ++;
    }

    public Entity(
            String name,
            Position position,
            int direction,
            float orientation,
            Connection connections,
            ControlBehaviour control_behaviour
    ) {
        this.entity_number = entity_count ++;
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.orientation = orientation;
        this.connections = connections;
        this.control_behaviour = control_behaviour;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public float getOrientation() {
        return orientation;
    }

    public void setOrientation(float orientation) {
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
