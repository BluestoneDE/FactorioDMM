package factorio.object;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Entity {
    private static int entity_count;
    public final int previous_number, next_number;
    @Expose
    public final int entity_number;
    @Expose
    private String name;
    @Expose
    private Position position;
    @Expose
    private Integer direction;
    @Expose
    private Float orientation;
    @Expose
    private ControlBehaviour control_behavior;
    @Expose
    private Connection connections;
    @Expose
    private ArrayList<Integer> neighbours;

    public Entity() {
        previous_number = entity_count;
        entity_number = ++entity_count;
        next_number = entity_count + 1;
    }

    public Entity(String name, Float x, Float y) {
        this();
        setName(name);
        setPosition(new Position(x, y));
    }

    public Entity(
            String name,
            Position position,
            Integer direction,
            ControlBehaviour controlBehavior,
            Connection connections
            ) {
        this();
        setName(name);
        setPosition(position);
        setDirection(direction);
        setControlBehavior(controlBehavior);
        setConnections(connections);
    }

    public static void resetEntityCount() {
        Entity.entity_count = 0;
    }

    @Deprecated
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
        this.neighbours = new ArrayList<>() {{
            addAll(neighbours);
        }};
    }
}
