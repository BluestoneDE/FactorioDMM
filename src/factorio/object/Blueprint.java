package factorio.object;

import java.util.ArrayList;

public final class Blueprint {
    public final String item = "blueprint";
    public String label;
    public ArrayList<Entity> entities;
    public Icon[] icons;
    public long version;

    public Blueprint() {
    }

    public Blueprint(
            String label,
            ArrayList<Entity> entities,
            Icon[] icons,
            long version
    ) {
        this.label = label;
        setEntities(entities);
        this.icons = icons;
        this.version = version;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        Entity.setEntityCount(0);
        this.entities = new ArrayList<>();
        for (Entity entity : entities) {
            this.entities.add(new Entity(
                    entity.getName(),
                    entity.getPosition(),
                    entity.getDirection(),
                    entity.getOrientation(),
                    entity.getControlBehavior(),
                    entity.getConnections()
            ));
        }
    }

    public Icon[] getIcons() {
        return icons;
    }

    public void setIcons(Icon[] icons) {
        this.icons = icons;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
