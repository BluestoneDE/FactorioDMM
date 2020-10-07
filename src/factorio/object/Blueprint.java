package factorio.object;

import java.util.ArrayList;

public final class Blueprint {
    public String item;
    public String label;
    public ArrayList<String> entities = new ArrayList<>();
    public ArrayList<String> icons = new ArrayList<>();
    public long version;

    public Blueprint() {}
    public Blueprint(String label) {
        this.label = label;
    }
    public Blueprint(String label, long version) {
        this.label = label;
        this.version = version;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<String> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<String> entities) {
        this.entities = entities;
    }

    public ArrayList<String> getIcons() {
        return icons;
    }

    public void setIcons(ArrayList<String> icons) {
        this.icons = icons;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
