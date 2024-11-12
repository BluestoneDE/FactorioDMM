package factorio.object.types;

import com.google.gson.annotations.SerializedName;

public enum Type {
    @SerializedName("virtual") VIRTUAL,
    @SerializedName("fluid") FLUID,
    @SerializedName("entity") ENTITY,
    @SerializedName("recipe") RECIPE,
    @SerializedName("quality") QUALITY,
}
