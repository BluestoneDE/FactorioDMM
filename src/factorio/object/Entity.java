package factorio.object;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Entity {
    private static int entity_count;
    public final int previous_number, next_number;
    @Expose
    public final int entity_number;
    @Expose
    protected String name;
    @Expose
    protected Position position;
    @Expose
    protected Integer direction;
    @Expose
    protected Float orientation;
    @Expose
    protected ControlBehaviour control_behavior;
    @Expose
    protected Connection connections;
    @Expose
    protected ArrayList<Integer> neighbours;

    public Entity() {
        previous_number = entity_count;
        entity_number = ++entity_count;
        next_number = entity_count + 1;
    }

    public Entity(
            String name,
            Position position,
            Integer direction,
            Float orientation,
            ControlBehaviour controlBehavior,
            Connection connections,
            ArrayList<Integer> neighbours
            ) {
        this();
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.orientation = orientation;
        this.control_behavior = controlBehavior;
        this.connections = connections;
        this.neighbours = neighbours;
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
}
