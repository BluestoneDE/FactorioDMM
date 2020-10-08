package factorio.object;

public final class Blueprint {
    public final String item = "blueprint";
    public String label;
    public Entity[] entities;
    public Icon[] icons;
    public long version;

    public Blueprint() {}

    public Blueprint(
            String label,
            Entity[] entities,
            Icon[] icons,
            long version
    ) {
        this.label = label;
        this.entities = entities;
        this.icons = icons;
        this.version = version;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Entity[] getEntities() {
        return entities;
    }

    public void setEntities(Entity[] entities) {
        this.entities = entities;
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
