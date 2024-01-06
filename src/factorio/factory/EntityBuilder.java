package factorio.factory;

import factorio.object.Connection;
import factorio.object.ControlBehaviour;
import factorio.object.Entity;
import factorio.object.Position;

import java.util.ArrayList;

public class EntityBuilder {
    public final int entity_number = Entity.getEntityCount() + 1;
    public final int next_number = Entity.getEntityCount() + 2, previous_number = Entity.getEntityCount();
    private String name = "wooden-chest";
    private Position position = new Position(0f,0f);
    private Integer direction;
    private Float orientation;
    private ControlBehaviour controlBehavior;
    private Connection connections;
    private ArrayList<Integer> neighbours;

    public EntityBuilder() {}
    public EntityBuilder(String name, Position position) {
        setName(name).setPosition(position);
    }

    public EntityBuilder(String name, Float x, Float y) {
        this(name, new Position(x, y));
    }

    public Entity build() {
        Entity entity = new Entity();
        entity.setName(name);
        entity.setPosition(position);
        if (direction != null) entity.setDirection(direction);
        if (orientation != null) entity.setOrientation(orientation);
        if (controlBehavior != null) entity.setControlBehavior(controlBehavior);
        if (connections != null) entity.setConnections(connections);
        if (neighbours != null) entity.setNeighbours(neighbours);
        return entity;
    }

    public EntityBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EntityBuilder setPosition(Position position) {
        this.position = position;
        return this;
    }

    public EntityBuilder setDirection(Integer direction) {
        this.direction = direction;
        return this;
    }

    public EntityBuilder setOrientation(Float orientation) {
        this.orientation = orientation;
        return this;
    }

    public EntityBuilder setControlBehavior(ControlBehaviour controlBehavior) {
        this.controlBehavior = controlBehavior;
        return this;
    }

    public EntityBuilder setConnections(Connection connections) {
        this.connections = connections;
        return this;
    }

    public EntityBuilder setNeighbours(ArrayList<Integer> neighbours) {
        this.neighbours = neighbours;
        return this;
    }

    public EntityBuilder addNeighbour(Integer neighbour) {
        if (neighbours == null) setNeighbours(new ArrayList<>());
        neighbours.add(neighbour);
        return this;
    }
}
