package factorio.factory;

import factorio.object.Connection;
import factorio.object.ControlBehaviour;
import factorio.object.Entity;
import factorio.object.Position;

import java.util.ArrayList;

public class EntityBuilder {
    public final int entity_number = Entity.getEntityCount() + 1;
    public final int next_number = Entity.getEntityCount() + 2, previous_number = Entity.getEntityCount();
    public String name = "wooden-chest";
    public Position position = new Position(0f, 0f);
    public Integer direction;
    public Float orientation;
    public ControlBehaviour controlBehavior;
    public Connection connections;
    public ArrayList<Integer> neighbours;

    public EntityBuilder() {
    }

    public EntityBuilder(String name, Position position) {
        setName(name).setPosition(position);
    }

    public EntityBuilder(String name, Float x, Float y) {
        this(name, new Position(x, y));
    }

    public Entity build() {
        return new Entity(name, position, direction, orientation, controlBehavior, connections, neighbours);
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
