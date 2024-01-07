package factorio.object;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public final class Blueprint {
    @Expose
    private final String item = "blueprint";
    @Expose
    public String label;
    @Expose
    public ArrayList<Entity> entities;
    @Expose
    public Icon[] icons;
    @Expose
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
        this.entities = entities;
        this.icons = icons;
        this.version = version;
    }
}
