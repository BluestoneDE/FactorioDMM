package factorio.object;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public final class Blueprint {
    @Expose
    private final String item = "blueprint";
    @Expose
    private String label;
    @Expose
    private ArrayList<Entity> entities;
    @Expose
    private Icon[] icons;
    @Expose
    private long version;

    public Blueprint() {
    }

    public Blueprint(
            String label,
            ArrayList<Entity> entities,
            Icon[] icons,
            long version
    ) {
        setLabel(label);
        setEntities(entities);
        setIcons(icons);
        setVersion(version);
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
        this.entities = new ArrayList<Entity>() {{
            addAll(entities);
        }};
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
