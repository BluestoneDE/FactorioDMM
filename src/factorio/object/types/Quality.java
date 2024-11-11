package factorio.object.types;

import com.google.gson.annotations.SerializedName;

public enum Quality implements Type {
    // qualities
    @SerializedName("normal")
    NORMAL,
    @SerializedName("uncommon")
    UNCOMMON,
    @SerializedName("rare")
    RARE,
    @SerializedName("epic")
    EPIC,
    @SerializedName("legendary")
    LEGENDARY,
    @SerializedName("unknown")
    QUALITY_UNKNOWN;

    @Override
    public String getType() {
        return "quality";
    }
}
