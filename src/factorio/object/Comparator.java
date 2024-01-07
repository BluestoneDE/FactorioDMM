package factorio.object;

import com.google.gson.annotations.SerializedName;

public enum Comparator {
    @SerializedName(">")
    GREATER_THAN,
    @SerializedName("<")
    LESS_THAN,
    @SerializedName(">=")
    GREATER_THAN_OR_EQUAL,
    @SerializedName("<=")
    LESS_THAN_OR_EQUAL,
    @SerializedName("=")
    EQUAL,
    @SerializedName("≠")
    NOT_EQUAL;
}
