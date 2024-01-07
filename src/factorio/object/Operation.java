package factorio.object;

import com.google.gson.annotations.SerializedName;

public enum Operation {
    @SerializedName("+")
    ADDITION,
    @SerializedName("-")
    SUBTRACTION,
    @SerializedName("*")
    MULTIPLICATION,
    @SerializedName("/")
    DIVISION,
    @SerializedName("%")
    MODULO,
    @SerializedName("^")
    POWER,
    @SerializedName("^")
    EXPONENTIATION,
    @SerializedName("<<")
    LEFT_SHIFT,
    @SerializedName(">>")
    RIGHT_SHIFT,
    @SerializedName("AND")
    BITWISE_AND,
    @SerializedName("OR")
    BITWISE_OR,
    @SerializedName("XOR")
    BITWISE_XOR;
}
