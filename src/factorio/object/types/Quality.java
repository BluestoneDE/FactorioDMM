package factorio.object.types;

import com.google.gson.annotations.SerializedName;

public enum Quality implements TypeInterface {
    // qualities
    @SerializedName("normal") NORMAL,
    @SerializedName("uncommon") UNCOMMON,
    @SerializedName("rare") RARE,
    @SerializedName("epic") EPIC,
    @SerializedName("legendary") LEGENDARY,
    // Unknown
    @SerializedName("quality-unknown") UNKNOWN,
    // Any Quality: used for signals
    @SerializedName("signal-any-quality") ANY {
        @Override
        public Type getType() {
            return Type.VIRTUAL;
        }
    };

    @Override
    public Type getType() {
        return Type.QUALITY;
    }
}
