package factorio.object.types;

import com.google.gson.annotations.SerializedName;

public enum Entity implements TypeInterface {
    // Unsorted
    @SerializedName("entity-ghost") GHOST,
    @SerializedName("item-on-ground") ITEM_ON_GROUND,
    @SerializedName("item-request-proxy") ITEM_REQUEST_PROXY,
    @SerializedName("tile-ghost") TILE_GHOST,
    @SerializedName("wube-logo-space-platform") WUBE_LOGO_SPACE_PLATFORM,
    // Unknown
    @SerializedName("entity-unknown") UNKNOWN
    ;

    @Override
    public Type getType() {
        return Type.ENTITY;
    }
}
